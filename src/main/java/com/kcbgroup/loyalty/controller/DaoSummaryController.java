package com.kcbgroup.loyalty.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.kcbgroup.loyalty.model.DaoSummary;
import com.kcbgroup.loyalty.model.User;
import com.kcbgroup.loyalty.repository.DaoRepository;
import com.kcbgroup.loyalty.repository.DaoSummaryRepository;
import com.kcbgroup.loyalty.repository.UserRepository;

@Controller
public class DaoSummaryController {
	
	@Autowired
	private DaoRepository daoRepository;
	
	@Autowired
	private DaoSummaryRepository daoSummaryRepository;
	
	@Autowired
	private UserRepository userRepository;
    
    @ResponseBody
	@GetMapping("/list-dao-summary-grid")
	public ResponseEntity<List<DaoSummary>> listDaoSummaryGrid(Authentication authentication) {
		Optional<User> user = userRepository.findByUsername(authentication.getName());
		String dao = (user.isPresent() ? user.get().getDao() : "");
		List<DaoSummary> daoSummary;
		
    	if (dao.isEmpty()) {
    		daoSummary = null;
    	}
    	else {
    		if (dao.equals("0000")) {
    			daoSummary = daoSummaryRepository.findAll();
    		}
    		else {
    			daoSummary = daoSummaryRepository.findByDao(dao);
    		}
    		
    		daoSummary.forEach(summary -> {
    			Optional<Dao> dao2 = daoRepository.findByBranchCode(summary.getDao());
    			if (dao2.isPresent()) summary.setDao(dao2.get().getBranchName());
    		});
    	}
    	
    	return ResponseEntity.ok(daoSummary);
	}
    
    @Transactional
    @PostMapping("/edit-dao-summary")
    public String editDaoSummary(@ModelAttribute DaoSummary daoSummary, HttpServletRequest request, 
    		RedirectAttributes redirectAttributes) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			DaoSummary daoSummaryEdit = daoSummaryRepository.getOne(id);
			
			daoSummaryEdit.setDao(daoSummary.getDao());
			daoSummaryEdit.setAmount1(daoSummary.getAmount1());
			daoSummaryEdit.setAmount2(daoSummary.getAmount2());
			daoSummaryEdit.setTotalPoints(daoSummary.getTotalPoints());
			daoSummaryEdit.setPosting75(daoSummary.getPosting75());
			daoSummaryEdit.setPosting25(daoSummary.getPosting25());
			daoSummaryEdit.setProfitLossAc(daoSummary.getProfitLossAc());
			daoSummaryEdit.setSuspenseAc(daoSummary.getSuspenseAc());
			daoSummaryEdit.setHoldingAc(daoSummary.getHoldingAc());
			daoSummaryEdit.setPostingDate(daoSummary.getPostingDate());
			daoSummaryEdit.setT24Ref(daoSummary.getT24Ref());
			daoSummaryEdit.setMessageId(daoSummary.getMessageId());
			daoSummaryEdit.setFileId(daoSummary.getFileId());
			daoSummaryEdit.setDescription(daoSummary.getDescription());
			daoSummaryEdit.setReason(daoSummary.getReason());
			daoSummaryRepository.save(daoSummaryEdit);
			redirectAttributes.addFlashAttribute("gridSuccess", "DAO summary edited successfully");
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("T24POSTING_UK1")) {
    			redirectAttributes.addFlashAttribute("gridFailure", daoSummary.getDao() + " already exists");
    		}
    		else {
    			redirectAttributes.addFlashAttribute("gridFailure", e.getMessage());
    		}
    	}
    	
        return "redirect:/dao-summary";
    }
    
    @ResponseBody
    @DeleteMapping("/delete-dao-summary/{id}")
	public ResponseEntity<String> deleteDaoSummary(@PathVariable Long id) {
		try {
			daoSummaryRepository.deleteById(id);
    		return ResponseEntity.ok("DAO summary deleted successfully");
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
	}
    
    @GetMapping("/dao-summary")
	public String daoSummary() {
    	return "summaries/dao-summary/daoSummary";
    }

}
