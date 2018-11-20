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
import com.kcbgroup.loyalty.model.Kcb;
import com.kcbgroup.loyalty.model.Upload;
import com.kcbgroup.loyalty.model.User;
import com.kcbgroup.loyalty.repository.DaoRepository;
import com.kcbgroup.loyalty.repository.KcbRepository;
import com.kcbgroup.loyalty.repository.UploadRepository;
import com.kcbgroup.loyalty.repository.UserRepository;
import com.kcbgroup.loyalty.service.StorageService;
import com.kcbgroup.loyalty.specification.KcbSpecification;
import com.kcbgroup.loyalty.specification.SearchCriteria;
import com.kcbgroup.loyalty.specification.UploadSpecification;

@Controller
public class KcbController {
	
	@Autowired
	private DaoRepository daoRepository;
	
	@Autowired
	private KcbRepository kcbRepository;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private UploadRepository uploadRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@ResponseBody
	@GetMapping("/list-upload-kcb-grid")
	public DataTablesOutput<Upload> listUploadKcbGrid(DataTablesInput input) {
    	String type = "KCB";
    	SearchCriteria criteria = new SearchCriteria();
		criteria.setKey("type");
		criteria.setOperation(":");
		criteria.setValue(type);
		UploadSpecification specification = new UploadSpecification(criteria);
		
		return uploadRepository.findAll(input, specification);
	}
	
    @ResponseBody
	@GetMapping("/list-records-kcb-grid")
	public DataTablesOutput<Kcb> listRecordsKcbGrid(DataTablesInput input, Authentication authentication) {
    	Optional<User> user = userRepository.findByUsername(authentication.getName());
    	String depositDao = (user.isPresent() ? user.get().getDao() : "");
    	DataTablesOutput<Kcb> kcb;
    	
    	if (depositDao.isEmpty()) {
    		kcb = null;
    	}
    	else {
    		if (depositDao.equals("0000")) {
    			kcb = kcbRepository.findAll(input);
    		}
    		else {
    			SearchCriteria criteria = new SearchCriteria();
    			criteria.setKey("depositDao");
				criteria.setOperation(":");
				criteria.setValue(depositDao);
				
    			KcbSpecification specification = new KcbSpecification(criteria);
    			kcb = kcbRepository.findAll(input, specification);
    		}
    		
    		kcb.getData().forEach(record -> {
				Optional<Dao> dao2 = daoRepository.findByBranchCode(record.getDepositDao());
				if (dao2.isPresent()) record.setDepositDao(dao2.get().getBranchName());
			});
    	}
    	
		return kcb;
	}
    
    @Transactional
    @PostMapping("/edit-kcb-record-form")
    public String editKcbRecordForm(@ModelAttribute Kcb kcb, HttpServletRequest request, 
    		RedirectAttributes redirectAttributes) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			Optional<Kcb> kcb2 = kcbRepository.findById(id);
			
			if (kcb2.isPresent()) {
				Kcb kcbEdit = kcb2.get();
				
				kcbEdit.setFileId(kcb.getFileId());
				kcbEdit.setRecordNo(kcb.getRecordNo());
				kcbEdit.setCustomerNo(kcb.getCustomerNo());
				kcbEdit.setMainTransCode(kcb.getMainTransCode());
				kcbEdit.setValueX1(kcb.getValueX1());
				kcbEdit.setAccountType(kcb.getAccountType());
				kcbEdit.setTransCode(kcb.getTransCode());
				kcbEdit.setIncomingDate(kcb.getIncomingDate());
				kcbEdit.setIncomingTime(kcb.getIncomingTime());
				kcbEdit.setTransRef(kcb.getTransRef());
				kcbEdit.setAccountNo(kcb.getAccountNo());
				kcbEdit.setNarration(kcb.getNarration());
				kcbEdit.setDepositDao(kcb.getDepositDao());
				kcbEdit.setValueX2(kcb.getValueX2());
				kcbEdit.setValueX3(kcb.getValueX3());
				kcbEdit.setValueX4(kcb.getValueX4());
				kcbEdit.setDrValue(kcb.getDrValue());
				kcbEdit.setValueX5(kcb.getValueX5());
				kcbEdit.setAmount1(kcb.getAmount1());
				kcbEdit.setAmount2(kcb.getAmount2());
				kcbEdit.setCurrency(kcb.getCurrency());
				kcbEdit.setMatched(kcb.getMatched());
				kcbEdit.setCheckFlag(kcb.getCheckFlag());
				kcbEdit.setPointsEarned(kcb.getPointsEarned());
				kcbEdit.setClosed(kcb.getClosed());
				kcbEdit.setSuccessErrorFlag(kcb.getSuccessErrorFlag());
				kcbEdit.setGenerated(kcb.getGenerated());
				kcbEdit.setProcessed(kcb.getProcessed());
				kcbEdit.setReturned(kcb.getReturned());
				kcbRepository.save(kcbEdit);
				redirectAttributes.addFlashAttribute("gridSuccess", "Record edited successfully");
			}
			else {
				redirectAttributes.addFlashAttribute("gridFailure", "Record does not exist");
			}
    	}
    	catch(Exception e) {
    		redirectAttributes.addFlashAttribute("gridFailure", e.getMessage());
    	}
    	
        return "redirect:/records-kcb";
    }
    
    @ResponseBody
    @DeleteMapping("/delete-kcb-record/{id}")
	public ResponseEntity<String> deleteKcbRecord(@PathVariable Long id) {
		try {
			kcbRepository.deleteById(id);
			return ResponseEntity.ok("Record deleted successfully");
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
	}
	
	@GetMapping("/upload-kcb")
	public String kcbUpload() {
		return "uploads/kcb/uploadKcb";
	}
	
	@GetMapping("/records-kcb")
	public String kcbRecords() {
		return "uploads/kcb/recordsKcb";
	}
	
	@ResponseBody
	@GetMapping("/download-kcb-file/{fileName:.+}")
    public ResponseEntity<Object> downloadKcbFile(@PathVariable String fileName, HttpServletRequest request) {
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
	@DeleteMapping("/delete-kcb-file/{id}")
	public ResponseEntity<String> deleteKcbFile(@PathVariable Long id) {
		try {
			uploadRepository.deleteById(id);
			return ResponseEntity.ok("File deleted successfully");
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
	}
	
}
