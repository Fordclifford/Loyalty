package com.kcbgroup.loyalty.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcbgroup.loyalty.model.Customer;
import com.kcbgroup.loyalty.model.Dao;
import com.kcbgroup.loyalty.model.Kcb;
import com.kcbgroup.loyalty.model.RedemptionDetails;
import com.kcbgroup.loyalty.model.RedemptionSummary;
import com.kcbgroup.loyalty.model.TransSummary;
import com.kcbgroup.loyalty.model.User;
import com.kcbgroup.loyalty.repository.CustomerRepository;
import com.kcbgroup.loyalty.repository.DaoRepository;
import com.kcbgroup.loyalty.repository.KcbRepository;
import com.kcbgroup.loyalty.repository.RedemptionDetailsRepository;
import com.kcbgroup.loyalty.repository.RedemptionSummaryRepository;
import com.kcbgroup.loyalty.repository.TransSummaryRepository;
import com.kcbgroup.loyalty.repository.UserRepository;
import com.kcbgroup.loyalty.specification.CustomerSpecification;
import com.kcbgroup.loyalty.specification.KcbSpecification;
import com.kcbgroup.loyalty.specification.RedemptionSummarySpecification;
import com.kcbgroup.loyalty.specification.SearchCriteria;

@Controller
public class ReportsController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private DaoRepository daoRepository;
	
	@Autowired
	private KcbRepository kcbRepository;
	
	@Autowired
	private RedemptionDetailsRepository redemptionDetailsRepository;
	
	@Autowired
	private RedemptionSummaryRepository redemptionSummaryRepository;
	
	@Autowired
	private TransSummaryRepository transSummaryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@ResponseBody
	@GetMapping("/reports/list-redemption-summary-report-grid")
	public DataTablesOutput<RedemptionSummary> listRedemptionSummaryReportGrid(
			DataTablesInput input, Authentication authentication) {
		Optional<User> user = userRepository.findByUsername(authentication.getName());
		String dao = (user.isPresent() ? user.get().getDao() : "");
		DataTablesOutput<RedemptionSummary> redemptionSummary;
		
		if (dao.isEmpty()) {
			redemptionSummary = null;
    	}
    	else {
    		if (dao.equals("0000")) {
    			redemptionSummary = redemptionSummaryRepository.findAll(input);
    		}
    		else {
    			SearchCriteria criteria = new SearchCriteria();
    			criteria.setKey("dao");
				criteria.setOperation(":");
				criteria.setValue(dao);
				
				RedemptionSummarySpecification specification = new RedemptionSummarySpecification(criteria);
				redemptionSummary = redemptionSummaryRepository.findAll(input, specification);
    		}
    		
    		redemptionSummary.getData().forEach(summary -> {
				Optional<Dao> dao2 = daoRepository.findByBranchCode(summary.getDao());
				if (dao2.isPresent()) summary.setDao(dao2.get().getBranchName());
			});
    	}
		
		return redemptionSummary;
	}
	
	@Transactional
	@ResponseBody
	@GetMapping("/reports/list-accrual-details-report-grid/{id}")
	public ResponseEntity<List<RedemptionDetails>> listAccrualDetailsReportGrid(@PathVariable Long id) {
		Optional<RedemptionSummary> optionalRedemptionSummary = redemptionSummaryRepository.findById(id);
		
		if (optionalRedemptionSummary.isPresent()) {
			RedemptionSummary redemptionSummary = optionalRedemptionSummary.get();
			String customerNo = redemptionSummary.getCustomerNo();
			List<RedemptionDetails> redemptionDetails = 
					redemptionDetailsRepository.findByCustomerNoAndPointsFlag(customerNo, "E");
			
			redemptionDetails.forEach(detail -> {
				Optional<Dao> dao = daoRepository.findByBranchCode(detail.getDao());
				if (dao.isPresent()) detail.setDao(dao.get().getBranchName());
			});
			
			return ResponseEntity.ok(redemptionDetails);
		}
		
		return null;
	}
	
	@Transactional
	@ResponseBody
	@GetMapping("/reports/list-redemption-details-report-grid/{id}")
	public ResponseEntity<List<RedemptionDetails>> listRedemptionDetailsReportGrid(@PathVariable Long id) {
		Optional<RedemptionSummary> optionalRedemptionSummary = redemptionSummaryRepository.findById(id);
		
		if (optionalRedemptionSummary.isPresent()) {
			RedemptionSummary redemptionSummary = optionalRedemptionSummary.get();
			String customerNo = redemptionSummary.getCustomerNo();
			List<RedemptionDetails> redemptionDetails = 
					redemptionDetailsRepository.findByCustomerNoAndPointsFlag(customerNo, "R");
			
			redemptionDetails.forEach(detail -> {
				Optional<Dao> dao = daoRepository.findByBranchCode(detail.getDao());
				if (dao.isPresent()) detail.setDao(dao.get().getBranchName());
			});
			
			return ResponseEntity.ok(redemptionDetails);
		}
		
		return null;
	}
	
	@ResponseBody
	@GetMapping("/reports/list-member-report-grid")
	public DataTablesOutput<Customer> listMemberReportGrid(DataTablesInput input, Authentication authentication) {
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
	
	@ResponseBody
	@GetMapping("/reports/list-processing-report-grid")
	public DataTablesOutput<Kcb> listProcessingReportGrid(DataTablesInput input, Authentication authentication) {
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
    			SearchCriteria criteria1 = new SearchCriteria();
    			criteria1.setKey("depositDao");
				criteria1.setOperation(":");
				criteria1.setValue(depositDao);
				
				Integer returned = 0;
				SearchCriteria criteria2 = new SearchCriteria();
    			criteria2.setKey("returned");
				criteria2.setOperation(":");
				criteria2.setValue(returned);
				
    			KcbSpecification specification1 = new KcbSpecification(criteria1);
    			KcbSpecification specification2 = new KcbSpecification(criteria2);
    			kcb = kcbRepository.findAll(input, specification1, specification2);
    		}
    		
    		kcb.getData().forEach(record -> {
				Optional<Dao> dao2 = daoRepository.findByBranchCode(record.getDepositDao());
				if (dao2.isPresent()) record.setDepositDao(dao2.get().getBranchName());
			});
    	}
		
		return kcb;
	}
	
	@ResponseBody
	@GetMapping("/reports/list-transaction-report-grid")
	public ResponseEntity<List<TransSummary>> listTransactionReportGrid() {
		List<TransSummary> transSummary = transSummaryRepository.findAll();
		return ResponseEntity.ok(transSummary);
	}
	
	@GetMapping("/reports/accrual")
	public String getAccrualReport() {
		return "reports/accrual";
	}
	
	@GetMapping("/reports/redemption")
	public String getRedemptionReport() {
		return "reports/redemption";
	}
	
	@GetMapping("/reports/member")
	public String getMemberReport() {
		return "reports/member";
	}
	
	@GetMapping("/reports/points-balance")
	public String getPointsBalanceReport() {
		return "reports/pointsBalance";
	}
	
	@GetMapping("/reports/processing")
	public String getProcessingReport() {
		return "reports/processing";
	}
	
	@GetMapping("/reports/transaction")
	public String getTransactionReport() {
		return "reports/transaction";
	}

}
