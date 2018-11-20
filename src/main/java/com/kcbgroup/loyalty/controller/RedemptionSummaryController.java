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

import com.kcbgroup.loyalty.model.Dao;
import com.kcbgroup.loyalty.model.RedemptionDetails;
import com.kcbgroup.loyalty.model.RedemptionSummary;
import com.kcbgroup.loyalty.model.User;
import com.kcbgroup.loyalty.repository.DaoRepository;
import com.kcbgroup.loyalty.repository.RedemptionDetailsRepository;
import com.kcbgroup.loyalty.repository.RedemptionSummaryRepository;
import com.kcbgroup.loyalty.repository.UserRepository;
import com.kcbgroup.loyalty.specification.RedemptionSummarySpecification;
import com.kcbgroup.loyalty.specification.SearchCriteria;

@Controller
public class RedemptionSummaryController {
	
	@Autowired
	private DaoRepository daoRepository;
	
	@Autowired
	private RedemptionDetailsRepository redemptionDetailsRepository;
	
	@Autowired
	private RedemptionSummaryRepository redemptionSummaryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@ResponseBody
	@GetMapping("/list-redemption-summary-grid")
	public DataTablesOutput<RedemptionSummary> listRedemptionSummaryGrid(
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
	@GetMapping("/list-redemption-details-grid/{id}")
	public ResponseEntity<List<RedemptionDetails>> listRedemptionDetailsGrid(@PathVariable Long id) {
		Optional<RedemptionSummary> optionalRedemptionSummary = redemptionSummaryRepository.findById(id);
		
		if (optionalRedemptionSummary.isPresent()) {
			RedemptionSummary redemptionSummary = optionalRedemptionSummary.get();
			String customerNo = redemptionSummary.getCustomerNo();
			List<RedemptionDetails> redemptionDetails = redemptionDetailsRepository.findByCustomerNo(customerNo);
			
			redemptionDetails.forEach(detail -> {
				Optional<Dao> dao = daoRepository.findByBranchCode(detail.getDao());
				if (dao.isPresent()) detail.setDao(dao.get().getBranchName());
			});
			
			return ResponseEntity.ok(redemptionDetails);
		}
		
		return null;
	}
    
    @GetMapping("/redemption-summary")
	public String redemptionSummary() {
    	return "summaries/redemption-summary/redemptionSummary";
    }

}
