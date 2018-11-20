package com.kcbgroup.loyalty.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Splitter;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.kcbgroup.loyalty.configuration.SSHConfiguration;
import com.kcbgroup.loyalty.model.Customer;
import com.kcbgroup.loyalty.model.Infinia;
import com.kcbgroup.loyalty.model.Kcb;
import com.kcbgroup.loyalty.model.Upload;
import com.kcbgroup.loyalty.repository.CustomerRepository;
import com.kcbgroup.loyalty.repository.DirectoryRepository;
import com.kcbgroup.loyalty.repository.InfiniaRepository;
import com.kcbgroup.loyalty.repository.KcbRepository;
import com.kcbgroup.loyalty.repository.UploadRepository;
/*import com.kcbgroup.loyalty.webservice.T24PostingService;*/

import javassist.NotFoundException;

@Service
public class StorageService {
	
	private static final Logger LOG = LoggerFactory.getLogger(StorageService.class);
	private static final String TEMP_LOCATION = "/var/tmp";
	private static final String EXISTS = "%s already exists";
	private static final String UPLOADING = "Uploading %s...";
	private static final String UPLOADED = "Uploaded %s";
	private static final String READING = "Reading %s...";
	private static final String READ = "Read %s";
	private static final String READ_COMPLETE = "Read complete";
	private static final String CONTEXT = "context";
	
	@Value("${dir.prefix}")
    private String dirPrefix;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private DirectoryRepository directoryRepository;
	
	@Autowired
	private InfiniaRepository infiniaRepository;
	
	@Autowired
	private KcbRepository kcbRepository;
	
	@Autowired
	private SSHConfiguration sshConfiguration;
	
	/*@Autowired
	private T24PostingService t24PostingService;*/
	
	@Autowired
	private UploadRepository uploadRepository;
	
	private Path customerInLocation() {
		return Paths.get(directoryRepository.findAll().get(0).getCustomerInDir());
	}
	
	private Path customerBkLocation() {
		return Paths.get(directoryRepository.findAll().get(0).getCustomerBkDir());
	}
	
	private Path kcbInLocation() {
		return Paths.get(directoryRepository.findAll().get(0).getKcbInDir());
	}
	
	private Path kcbOutLocation() {
		return Paths.get(directoryRepository.findAll().get(0).getKcbOutDir());
	}
	
	private Path kcbBkLocation() {
		return Paths.get(directoryRepository.findAll().get(0).getKcbBkDir());
	}
	
	private Path infiniaInLocation() {
		return Paths.get(directoryRepository.findAll().get(0).getInfiniaInDir());
	}
	
	private Path infiniaBkLocation() {
		return Paths.get(directoryRepository.findAll().get(0).getInfiniaBkDir());
	}
	
	@Scheduled(fixedDelay = 15 * 60 * 1000)
	protected void scanCustomerDirectory() throws IOException, JSchException, SftpException {
		String identifier = "BNK_CUST";
		
		LOG.info("Accessing EDWH for customer files...");
		sshConfiguration.getFromEDWH(identifier);
		LOG.info("Access to EDWH complete");
		
		String customerInLocation = customerInLocation().toString();
		List<String> filenames = new ArrayList<>();
		
		try (Stream<Path> paths = Files.list(Paths.get(customerInLocation))) {
			Stream<Path> filteredPaths = paths.filter(Files::isRegularFile);
			filteredPaths.forEach(file -> filenames.add(file.getFileName().toString()));
		}
		
		if (filenames.isEmpty()) {
			LOG.info("No customer files found");
		}
		else {
			filenames.forEach(filename -> {
				Optional<Upload> upload = uploadRepository.findByName(filename);
				
				if (upload.isPresent()) {
					LOG.error(String.format(EXISTS, filename));
				}
				else {
					File file = new File(customerInLocation.concat("/").concat(filename));
					String flag = "CUSTOMER";
					
					LOG.info(String.format(UPLOADING, filename));
					try {
						uploadFile(file, flag);
					}
					catch (IOException e) {
						e.getMessage();
					}
					LOG.info(String.format(UPLOADED, filename));
					
					Path path = Paths.get(file.getAbsolutePath());
					
					LOG.info(String.format(READING, filename));
					try {
						readCustomerFile(path);
					}
					catch (IOException e) {
						e.getMessage();
					}
					LOG.info(String.format(READ, filename));
					
					String customerBkLocation = customerBkLocation().toString();
					
					Path source = Paths.get(customerInLocation.concat("/")).resolve(filename);
					Path target = Paths.get(customerBkLocation.concat("/")).resolve(filename);
					
					try {
						Files.move(source, target);
					}
					catch (IOException e) {
						LOG.error(CONTEXT, e);
					}
				}
			});
		}
	}
	
	@Scheduled(fixedDelay = 15 * 60 * 1000, initialDelay = 1 * 1000)
	protected void scanKcbDirectory() throws IOException, JSchException, SftpException {
		String identifier = dirPrefix.concat("BNKTXN");
		
		LOG.info("Accessing EDWH for KCB files...");
		sshConfiguration.getFromEDWH(identifier);
		LOG.info("Access to EDWH complete");
		
		String kcbInLocation = kcbInLocation().toString();
		List<String> filenames = new ArrayList<>();
		
		try (Stream<Path> paths = Files.list(Paths.get(kcbInLocation))) {
			Stream<Path> filteredPaths = paths.filter(Files::isRegularFile);
			filteredPaths.forEach(file -> filenames.add(file.getFileName().toString()));
		}
		
		if (filenames.isEmpty()) {
			LOG.info("No KCB files found");
		}
		else {
			filenames.forEach(filename -> {
				Optional<Upload> upload = uploadRepository.findByName(filename.concat(".txt"));
				
				if (upload.isPresent()) {
					LOG.error(String.format(EXISTS, filename));
				}
				else {
					String newFilename = filename.replace(dirPrefix, "").concat(".txt");
					Path source = Paths.get(kcbInLocation.concat("/")).resolve(filename);
					Path target = Paths.get(kcbInLocation.concat("/")).resolve(newFilename);
					
					try {
						Files.move(source, target);
					}
					catch (IOException e) {
						LOG.error(CONTEXT, e);
					}
					
					File file = new File(kcbInLocation.concat("/").concat(newFilename));
					String flag = "KCB";
					
					LOG.info(String.format(UPLOADING, newFilename));
					try {
						uploadFile(file, flag);
					}
					catch (IOException e) {
						e.getMessage();
					}
					LOG.info(String.format(UPLOADED, newFilename));
					
					Path path = Paths.get(file.getAbsolutePath());
					
					LOG.info(String.format(READING, newFilename));
					try {
						readKcbFile(path);
					}
					catch (IOException e) {
						e.getMessage();
					}
					LOG.info(String.format(READ, newFilename));
					
					String kcbOutLocation = kcbOutLocation().toString();
					
					Path source2 = Paths.get(kcbInLocation.concat("/")).resolve(newFilename);
					Path target2 = Paths.get(kcbOutLocation.concat("/")).resolve(newFilename);
					
					try {
						Files.move(source2, target2);
					}
					catch (IOException e) {
						LOG.error(CONTEXT, e);
					}
					
					LOG.info("Uploading KCB file back to server...");
					try {
						sshConfiguration.postToEDWH(newFilename);
					}
					catch (JSchException | SftpException e) {
						LOG.error(CONTEXT, e);
					}
					LOG.info("Upload complete");
					
					String kcbBkLocation = kcbBkLocation().toString();
					
					Path source3 = Paths.get(kcbOutLocation.concat("/")).resolve(newFilename);
					Path target3 = Paths.get(kcbBkLocation.concat("/")).resolve(newFilename);
					
					try {
						Files.move(source3, target3);
					}
					catch (IOException e) {
						LOG.error(CONTEXT, e);
					}
				}
			});
		}
	}
	
	@Scheduled(fixedDelay = 15 * 60 * 1000, initialDelay = 2 * 1000)
	protected void scanInfiniaDirectory() throws IOException {
		String infiniaInLocation = infiniaInLocation().toString();
		List<String> filenames = new ArrayList<>();
		
		try (Stream<Path> paths = Files.list(Paths.get(infiniaInLocation))) {
			Stream<Path> filteredPaths = paths.filter(Files::isRegularFile);
			filteredPaths.forEach(file -> filenames.add(file.getFileName().toString()));
		}
		
		if (filenames.isEmpty()) {
			LOG.info("No Infinia files found");
		}
		else {
			filenames.forEach(filename -> {
				Optional<Upload> upload = uploadRepository.findByName(filename);
				
				if (upload.isPresent()) {
					LOG.error(String.format(EXISTS, filename));
				}
				else {
					File file = new File(infiniaInLocation.concat("/").concat(filename));
					String flag = "INFINIA";
					
					LOG.info(String.format(UPLOADING, filename));
					try {
						uploadFile(file, flag);
					}
					catch (IOException e) {
						e.getMessage();
					}
					LOG.info(String.format(UPLOADED, filename));
					
					Path path = Paths.get(file.getAbsolutePath());
					
					LOG.info(String.format(READING, filename));
					try {
						readInfiniaFile(path);
					}
					catch (IOException e) {
						e.getMessage();
					}
					LOG.info(String.format(READ, filename));
					
					String infiniaBkLocation = infiniaBkLocation().toString();
					
					Path source = Paths.get(infiniaInLocation.concat("/")).resolve(filename);
					Path target = Paths.get(infiniaBkLocation.concat("/")).resolve(filename);
					
					try {
						Files.move(source, target);
					}
					catch (IOException e) {
						LOG.error(CONTEXT, e);
					}
				}
			});
		}
		
		LOG.info("Validating files and records...");
		kcbRepository.callIncomingKcbCursor();
		LOG.info("Validation complete");
		
		/*LOG.info("Posting to T24...");
		t24PostingService.post75ToT24();
		t24PostingService.post25ToT24();
		LOG.info("Posting done");*/
	}
	
	private void uploadFile(File file, String flag) throws IOException {
		String filename = file.getName();
		String extension = FilenameUtils.getExtension(filename);
		Path path = Paths.get(file.getAbsolutePath());
		
		if (file.length() == 0) {
			LOG.error("{} is empty", filename);
        }
		else if (!"txt".equalsIgnoreCase(extension)) {
			LOG.error("{} is not a text file", filename);
		}
		else if (file.length() > 20971520) {
			LOG.error("{} has exceeded the allowed size limit", filename);
    	}
		else {
			Upload upload = new Upload();
            upload.setName(filename);
            upload.setData(Files.readAllBytes(path));
            upload.setUploadDate(LocalDateTime.now());
            upload.setType(flag);
            upload.setStatus("Reading file...");
            uploadRepository.save(upload);
		}
	}
	
	@Transactional
	public void readCustomerFile(Path path) throws IOException {
		String filename = path.getFileName().toString();
		List<Customer> customers = new ArrayList<>();
		
		try (BufferedReader br = Files.newBufferedReader(path)) {
			List<String> list = br.lines().collect(Collectors.toList());
			
			list.forEach(
				record -> {
					List<String> subList = new ArrayList<>();
					subList = Splitter.on(',').trimResults().splitToList(record);
					
					try {
						Customer customer = new Customer();
						customer.setCustomerCode(subList.get(0));
						customer.setMnemonic(subList.get(1));
						customer.setShortName(subList.get(2));
						customer.setName(subList.get(3));
						customer.setOptInDate(subList.get(4));
						customer.setStreet(subList.get(5));
						customer.setAddress(subList.get(6));
						customer.setTown(subList.get(7));
						customer.setPostCode(subList.get(8));
						customer.setCountry(subList.get(9));
						customer.setSector(subList.get(10));
						customer.setAccountOfficer(subList.get(11));
						customer.setOtherOfficer(subList.get(12));
						customer.setIndustry(subList.get(13));
						customer.setTarget(subList.get(14));
						customer.setNationality(subList.get(15));
						customer.setCustomerStatus(subList.get(16));
						customer.setResidence(subList.get(17));
						customer.setContactDate(subList.get(18));
						customer.setCustOffice(subList.get(19));
						customer.setCustMobile(subList.get(20));
						customers.add(customer);
					}
					catch(Exception e) {
						if (e.getMessage().contains("CUSTOMERS_UK1")) {
							LOG.error(String.format(EXISTS, subList.get(0)));
			    		}
			    		else {
			    			e.getMessage();
			    		}
					}
				}
			);
		}
		
		customerRepository.saveAll(customers);
		Optional<Upload> upload = uploadRepository.findByName(filename);
		
		if (upload.isPresent()) {
			upload.get().setStatus(READ_COMPLETE);
			uploadRepository.save(upload.get());
		}
	}
	
	@Transactional
	public void readKcbFile(Path path) throws IOException {
		String filename = path.getFileName().toString();
		List<Kcb> kcbs = new ArrayList<>();
		
		try (BufferedReader br = Files.newBufferedReader(path)) {
			List<String> list = br.lines().collect(Collectors.toList());
			
			list.forEach(
				record -> {
					if (!record.isEmpty()) {
						List<String> subList = new ArrayList<>();
						subList = Splitter.on('|').trimResults().splitToList(record);
						String baseName = FilenameUtils.getBaseName(filename);
						
						Kcb kcb = new Kcb();
						kcb.setFileId(baseName);
						kcb.setRecordNo(Long.valueOf(subList.get(0)));
						kcb.setCustomerNo(subList.get(1));
						kcb.setMainTransCode(subList.get(2));
						kcb.setValueX1(subList.get(3));
						kcb.setAccountType(Integer.valueOf(subList.get(4)));
						kcb.setTransCode(subList.get(5));
						kcb.setIncomingDate(Integer.valueOf(subList.get(6)));
						kcb.setIncomingTime(Integer.valueOf(subList.get(7)));
						kcb.setTransRef(subList.get(8));
						kcb.setAccountNo(subList.get(9));
						kcb.setNarration(subList.get(11));
						kcb.setDepositDao(subList.get(12));
						kcb.setValueX2(subList.get(10));
						kcb.setValueX3(subList.get(13));
						kcb.setValueX4(subList.get(14));
						kcb.setDrValue(subList.get(15));
						kcb.setValueX5(subList.get(16));
						kcb.setAmount1(Double.valueOf(subList.get(18)));
						kcb.setAmount2(Double.valueOf(subList.get(18)));
						kcb.setCurrency(subList.get(19));
						kcb.setMatched("No");
						kcb.setCheckFlag("Open");
						kcb.setPointsEarned(0);
						kcb.setClosed("No");
						kcb.setSuccessErrorFlag("00");
						kcb.setGenerated(0);
						kcb.setProcessed(0);
						kcb.setReturned(0);
						kcbs.add(kcb);
					}
				}
			);
		}
		
		kcbRepository.saveAll(kcbs);
		Optional<Upload> upload = uploadRepository.findByName(filename);
		
		if (upload.isPresent()) {
			upload.get().setStatus(READ_COMPLETE);
			uploadRepository.save(upload.get());
		}
	}
	
	@Transactional
	public void readInfiniaFile(Path path) throws IOException {
		String filename = path.getFileName().toString();
		List<Infinia> infinias = new ArrayList<>();
		
		try (BufferedReader br = Files.newBufferedReader(path)) {
			List<String> list = br.lines().collect(Collectors.toList());
			
			list.forEach(
				record -> {
					if (!record.isEmpty()) {
						List<String> subList = new ArrayList<>();
						List<String> name = new ArrayList<>();
						subList = Splitter.on('|').trimResults().splitToList(record);
						name = Splitter.on("_LOG").trimResults().splitToList(filename);
						
						Infinia infinia = new Infinia();
						infinia.setFileId(name.get(0));
						infinia.setRecordNo(Long.valueOf(subList.get(0)));
						infinia.setCustomerNo(subList.get(1));
						infinia.setMainTransCode(subList.get(2));
						infinia.setValueX1(subList.get(3));
						infinia.setAccountType(Integer.valueOf(subList.get(4)));
						infinia.setTransCode(subList.get(5));
						infinia.setIncomingDate(Integer.valueOf(subList.get(6)));
						infinia.setIncomingTime(Integer.valueOf(subList.get(7)));
						infinia.setTransRef(subList.get(8));
						infinia.setAccountNo(subList.get(9));
						infinia.setNarration(subList.get(11));
						infinia.setDepositDao(subList.get(12));
						infinia.setValueX2(subList.get(10));
						infinia.setValueX3(subList.get(13));
						infinia.setValueX4(subList.get(14));
						infinia.setDrValue(subList.get(15));
						infinia.setValueX5(subList.get(16));
						infinia.setAmount1(Double.valueOf(subList.get(17)));
						infinia.setAmount2(Double.valueOf(subList.get(18)));
						infinia.setCurrency(subList.get(19));
						infinia.setValueX6(subList.get(20));
						infinia.setValueX7(subList.get(21));
						
						if (NumberUtils.isCreatable(subList.get(22))) {
							infinia.setPointsEarned(Integer.valueOf(subList.get(22)));
							infinia.setSuccessErrorFlag("00");
							infinia.setErrorCode("00");
						}
						else {
							infinia.setPointsEarned(0);
							infinia.setSuccessErrorFlag("16");
							infinia.setErrorCode(subList.get(22));
						}
						
						infinia.setMatched("No");
						infinia.setCheckFlag("Open");
						infinia.setClosed("No");
						infinia.setGenerated(0);
						infinia.setProcessed(0);
						infinia.setReturned(0);
						infinias.add(infinia);
					}
				}
			);
		}
		
		infiniaRepository.saveAll(infinias);
		Optional<Upload> upload = uploadRepository.findByName(filename);
		
		if (upload.isPresent()) {
			upload.get().setStatus(READ_COMPLETE);
			uploadRepository.save(upload.get());
		}
	}
	
	private Path loadFile(String filename) throws IOException {
		Optional<Upload> upload = uploadRepository.findByName(filename);
		Path path = Paths.get(TEMP_LOCATION).resolve(filename);
		byte[] bytes;
		
		if (upload.isPresent()) {
			bytes = upload.get().getData();
		}
		else {
			bytes = null;
		}
		
		Files.write(path, bytes);
		return path;
	}

	public Resource loadResource(String filename) throws IOException, NotFoundException {
        Path path = loadFile(filename);
        Resource resource = new UrlResource(path.toUri());
        
        if (resource.exists() || resource.isReadable()) {
            return resource;
        }
        else {
            throw new NotFoundException(String.format("Could not load file %s", filename));
        }
	}

}
