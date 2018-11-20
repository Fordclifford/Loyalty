package com.kcbgroup.loyalty.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kcbgroup.loyalty.model.Dao;
import com.kcbgroup.loyalty.model.Infinia;
import com.kcbgroup.loyalty.model.Upload;
import com.kcbgroup.loyalty.model.User;
import com.kcbgroup.loyalty.repository.DaoRepository;
import com.kcbgroup.loyalty.repository.InfiniaRepository;
import com.kcbgroup.loyalty.repository.UploadRepository;
import com.kcbgroup.loyalty.repository.UserRepository;
import com.kcbgroup.loyalty.service.StorageService;
import com.kcbgroup.loyalty.specification.InfiniaSpecification;
import com.kcbgroup.loyalty.specification.SearchCriteria;
import com.kcbgroup.loyalty.specification.UploadSpecification;

@Controller
public class InfiniaController {
	
	@Autowired
	private DaoRepository daoRepository;
	
	@Autowired
	private InfiniaRepository infiniaRepository;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private UploadRepository uploadRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@ResponseBody
	@GetMapping("/list-upload-infinia-grid")
	public DataTablesOutput<Upload> listUploadInfiniaGrid(DataTablesInput input) {
		String type = "INFINIA";
    	SearchCriteria criteria = new SearchCriteria();
		criteria.setKey("type");
		criteria.setOperation(":");
		criteria.setValue(type);
		UploadSpecification specification = new UploadSpecification(criteria);
		
		return uploadRepository.findAll(input, specification);
	}
	
    @ResponseBody
	@GetMapping("/list-records-infinia-grid")
	public DataTablesOutput<Infinia> listRecordsInfiniaGrid(DataTablesInput input, Authentication authentication) {
    	Optional<User> user = userRepository.findByUsername(authentication.getName());
    	String depositDao = (user.isPresent() ? user.get().getDao() : "");
    	DataTablesOutput<Infinia> infinia;
    	
    	if (depositDao.isEmpty()) {
    		infinia = null;
    	}
    	else {
    		if (depositDao.equals("0000")) {
    			infinia = infiniaRepository.findAll(input);
    		}
    		else {
    			SearchCriteria criteria = new SearchCriteria();
    			criteria.setKey("depositDao");
				criteria.setOperation(":");
				criteria.setValue(depositDao);
				
    			InfiniaSpecification specification = new InfiniaSpecification(criteria);
    			infinia = infiniaRepository.findAll(input, specification);
    		}
    		
    		infinia.getData().forEach(record -> {
				Optional<Dao> dao2 = daoRepository.findByBranchCode(record.getDepositDao());
				if (dao2.isPresent()) record.setDepositDao(dao2.get().getBranchName());
			});
    	}
    	
		return infinia;
	}
    
    @Transactional
    @PostMapping("/edit-infinia-record-form")
    public String editInfiniaRecordForm(@ModelAttribute Infinia infinia, HttpServletRequest request, 
    		RedirectAttributes redirectAttributes) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			Optional<Infinia> infinia2 = infiniaRepository.findById(id);
			
			if (infinia2.isPresent()) {
				Infinia infiniaEdit = infinia2.get();
				
				infiniaEdit.setFileId(infinia.getFileId());
				infiniaEdit.setRecordNo(infinia.getRecordNo());
				infiniaEdit.setCustomerNo(infinia.getCustomerNo());
				infiniaEdit.setMainTransCode(infinia.getMainTransCode());
				infiniaEdit.setValueX1(infinia.getValueX1());
				infiniaEdit.setAccountType(infinia.getAccountType());
				infiniaEdit.setTransCode(infinia.getTransCode());
				infiniaEdit.setIncomingDate(infinia.getIncomingDate());
				infiniaEdit.setIncomingTime(infinia.getIncomingTime());
				infiniaEdit.setTransRef(infinia.getTransRef());
				infiniaEdit.setAccountNo(infinia.getAccountNo());
				infiniaEdit.setNarration(infinia.getNarration());
				infiniaEdit.setDepositDao(infinia.getDepositDao());
				infiniaEdit.setValueX2(infinia.getValueX2());
				infiniaEdit.setValueX3(infinia.getValueX3());
				infiniaEdit.setValueX4(infinia.getValueX4());
				infiniaEdit.setDrValue(infinia.getDrValue());
				infiniaEdit.setValueX5(infinia.getValueX5());
				infiniaEdit.setAmount1(infinia.getAmount1());
				infiniaEdit.setAmount2(infinia.getAmount2());
				infiniaEdit.setCurrency(infinia.getCurrency());
				infiniaEdit.setValueX6(infinia.getValueX6());
				infiniaEdit.setValueX7(infinia.getValueX7());
				infiniaEdit.setPointsEarned(infinia.getPointsEarned());
				infiniaEdit.setSuccessErrorFlag(infinia.getSuccessErrorFlag());
				infiniaEdit.setMatched(infinia.getMatched());
				infiniaEdit.setCheckFlag(infinia.getCheckFlag());
				infiniaEdit.setClosed(infinia.getClosed());
				infiniaEdit.setErrorCode(infinia.getErrorCode());
				infiniaEdit.setGenerated(infinia.getGenerated());
				infiniaEdit.setProcessed(infinia.getProcessed());
				infiniaEdit.setReturned(infinia.getReturned());
				infiniaRepository.save(infiniaEdit);
				redirectAttributes.addFlashAttribute("gridSuccess", "Record edited successfully");
			}
			else {
				redirectAttributes.addFlashAttribute("gridFailure", "Record does not exist");
			}
    	}
    	catch(Exception e) {
    		redirectAttributes.addFlashAttribute("gridFailure", e.getMessage());
    	}
    	
        return "redirect:/records-infinia";
    }
    
    @ResponseBody
    @DeleteMapping("/delete-infinia-record/{id}")
	public ResponseEntity<String> deleteInfiniaRecord(@PathVariable Long id) {
		try {
			infiniaRepository.deleteById(id);
			return ResponseEntity.ok("Record deleted successfully");
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
	}
    
    @GetMapping("/upload-infinia")
	public String infiniaUpload() {
		return "uploads/infinia/uploadInfinia";
	}
    
    @GetMapping("/records-infinia")
	public String infiniaRecords() {
		return "uploads/infinia/recordsInfinia";
	}
	
	@ResponseBody
	@GetMapping("/download-infinia-file/{fileName:.+}")
    public ResponseEntity<Object> downloadInfiniaFile(@PathVariable String fileName, HttpServletRequest request) {
        try {
        	Resource resource = storageService.loadResource(fileName);
        	String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
    }
	
	@ResponseBody
	@DeleteMapping("/delete-infinia-file/{id}")
	public ResponseEntity<String> deleteInfiniaFile(@PathVariable Long id) {
		try {
			uploadRepository.deleteById(id);
			return ResponseEntity.ok("File deleted successfully");
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
	}

}
