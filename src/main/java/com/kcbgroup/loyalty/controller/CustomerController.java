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

import com.kcbgroup.loyalty.model.Customer;
import com.kcbgroup.loyalty.model.Dao;
import com.kcbgroup.loyalty.model.Upload;
import com.kcbgroup.loyalty.model.User;
import com.kcbgroup.loyalty.repository.CustomerRepository;
import com.kcbgroup.loyalty.repository.DaoRepository;
import com.kcbgroup.loyalty.repository.UploadRepository;
import com.kcbgroup.loyalty.repository.UserRepository;
import com.kcbgroup.loyalty.service.StorageService;
import com.kcbgroup.loyalty.specification.CustomerSpecification;
import com.kcbgroup.loyalty.specification.SearchCriteria;
import com.kcbgroup.loyalty.specification.UploadSpecification;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private DaoRepository daoRepository;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private UploadRepository uploadRepository;
	
	@Autowired
	private UserRepository userRepository;
    
	@ResponseBody
	@GetMapping("/list-upload-customer-grid")
	public DataTablesOutput<Upload> listUploadCustomerGrid(DataTablesInput input) {
    	String type = "CUSTOMER";
    	SearchCriteria criteria = new SearchCriteria();
		criteria.setKey("type");
		criteria.setOperation(":");
		criteria.setValue(type);
		UploadSpecification specification = new UploadSpecification(criteria);
		
		return uploadRepository.findAll(input, specification);
	}
    
    @ResponseBody
	@GetMapping("/list-records-customer-grid")
	public DataTablesOutput<Customer> listRecordsCustomerGrid(
			DataTablesInput input, Authentication authentication) {
    	Optional<User> user = userRepository.findByUsername(authentication.getName());
		String accountOfficer = (user.isPresent() ? user.get().getDao() : "");
		DataTablesOutput<Customer> customers;
		
		if (accountOfficer.isEmpty()) {
			customers = null;
    	}
    	else {
    		if (accountOfficer.equals("0000")) {
    			customers = customerRepository.findAll(input);
    		}
    		else {
    			SearchCriteria criteria = new SearchCriteria();
    			criteria.setKey("accountOfficer");
				criteria.setOperation(":");
				criteria.setValue(accountOfficer);
				
    			CustomerSpecification specification = new CustomerSpecification(criteria);
    			customers = customerRepository.findAll(input, specification);
    		}
    		
    		customers.getData().forEach(customer -> {
				Optional<Dao> dao2 = daoRepository.findByBranchCode(customer.getAccountOfficer());
				if (dao2.isPresent()) customer.setAccountOfficer(dao2.get().getBranchName());
			});
    	}
		
		return customers;
	}
    
    @Transactional
    @PostMapping("/edit-customer-record-form")
    public String editCustomerRecordForm(@ModelAttribute Customer customer, HttpServletRequest request, 
    		RedirectAttributes redirectAttributes) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			Optional<Customer> customer2 = customerRepository.findById(id);
			
			if (customer2.isPresent()) {
				Customer customerEdit = customer2.get();
				
				customerEdit.setCustomerCode(customer.getCustomerCode());
				customerEdit.setMnemonic(customer.getMnemonic());
				customerEdit.setShortName(customer.getShortName());
				customerEdit.setName(customer.getName());
				customerEdit.setOptInDate(customer.getOptInDate());
				customerEdit.setStreet(customer.getStreet());
				customerEdit.setAddress(customer.getAddress());
				customerEdit.setTown(customer.getTown());
				customerEdit.setPostCode(customer.getPostCode());
				customerEdit.setCountry(customer.getCountry());
				customerEdit.setSector(customer.getSector());
				customerEdit.setAccountOfficer(customer.getAccountOfficer());
				customerEdit.setOtherOfficer(customer.getOtherOfficer());
				customerEdit.setIndustry(customer.getIndustry());
				customerEdit.setTarget(customer.getTarget());
				customerEdit.setNationality(customer.getNationality());
				customerEdit.setCustomerStatus(customer.getCustomerStatus());
				customerEdit.setResidence(customer.getResidence());
				customerEdit.setContactDate(customer.getContactDate());
				customerEdit.setCustOffice(customer.getCustOffice());
				customerEdit.setCustMobile(customer.getCustMobile());
				customerRepository.save(customerEdit);
				redirectAttributes.addFlashAttribute("gridSuccess", "Customer edited successfully");
			}
			else {
				redirectAttributes.addFlashAttribute("gridFailure", customer.getCustomerCode() + " does not exist");
			}
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("CUSTOMERS_UK1")) {
    			redirectAttributes.addFlashAttribute("gridFailure", customer.getCustomerCode() + " already exists");
    		}
    		else {
    			redirectAttributes.addFlashAttribute("gridFailure", e.getMessage());
    		}
    	}
    	
        return "redirect:/records-customer";
    }
    
    @ResponseBody
    @DeleteMapping("/delete-customer-record/{id}")
	public ResponseEntity<String> deleteCustomerRecord(@PathVariable Long id) {
		try {
			customerRepository.deleteById(id);
			return ResponseEntity.ok("Customer deleted successfully");
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
	}
    
    @GetMapping("/upload-customer")
	public String customerUpload() {
		return "uploads/customer/uploadCustomer";
	}
    
    @GetMapping("/records-customer")
	public String customerRecords() {
		return "uploads/customer/recordsCustomer";
	}
    
    @ResponseBody
	@GetMapping("/download-customer-file/{fileName:.+}")
    public ResponseEntity<Object> downloadCustomerFile(@PathVariable String fileName, HttpServletRequest request) {
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
	@DeleteMapping("/delete-customer-file/{id}")
	public ResponseEntity<String> deleteCustomerFile(@PathVariable Long id) {
    	try {
			uploadRepository.deleteById(id);
			return ResponseEntity.ok("File deleted successfully");
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
	}

}
