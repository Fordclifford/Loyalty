package com.kcbgroup.loyalty.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kcbgroup.loyalty.model.Dao;
import com.kcbgroup.loyalty.repository.DaoRepository;

@Controller
public class DaoController {
    
	@Autowired
    private DaoRepository daoRepository;
    
    @ResponseBody
	@GetMapping("/list-dao-grid")
	public ResponseEntity<List<Dao>> listDaoGrid() {
		List<Dao> dao = daoRepository.findAll();
		return ResponseEntity.ok(dao);
	}
    
    @Transactional
    @PostMapping("/edit-dao-form")
    public String editDaoForm(@ModelAttribute Dao dao, HttpServletRequest request, 
    		RedirectAttributes redirectAttributes) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			Dao daoEdit = daoRepository.getOne(id);
			
			daoEdit.setBranchCode(dao.getBranchCode());
			daoEdit.setBranchName(dao.getBranchName());
			daoEdit.setProfitLossAc(dao.getProfitLossAc());
			daoEdit.setLoyaltyAc(dao.getLoyaltyAc());
			daoEdit.setHoldingAc(dao.getHoldingAc());
			daoRepository.save(daoEdit);
			redirectAttributes.addFlashAttribute("gridSuccess", "DAO edited successfully");
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("DAO_UK1")) {
    			redirectAttributes.addFlashAttribute("gridFailure", dao.getBranchCode() + " already exists");
    		}
    		else {
    			redirectAttributes.addFlashAttribute("gridFailure", e.getMessage());
    		}
    	}
    	
        return "redirect:/dao-grid";
    }
    
    @ResponseBody
    @DeleteMapping("/delete-dao/{id}")
	public ResponseEntity<String> deleteDao(@PathVariable Long id) {
		try {
			daoRepository.deleteById(id);
			return ResponseEntity.ok("DAO deleted successfully");
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
	}
	
	@GetMapping("/dao-form")
    public String getDaoForm(Model model) {
        model.addAttribute("dao", new Dao());
        return "parameters/dao/daoForm";
    }

    @PostMapping("/dao-form")
    public String postDaoForm(@ModelAttribute Dao dao, RedirectAttributes redirectAttributes) {
		try {
			Dao daoNew = new Dao();
			daoNew.setBranchCode(dao.getBranchCode());
			daoNew.setBranchName(dao.getBranchName());
			daoNew.setProfitLossAc(dao.getProfitLossAc());
			daoNew.setLoyaltyAc(dao.getLoyaltyAc());
			daoNew.setHoldingAc(dao.getHoldingAc());
			daoRepository.save(daoNew);
			redirectAttributes.addFlashAttribute("formSuccess", "DAO captured successfully");
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("DAO_UK1")) {
    			redirectAttributes.addFlashAttribute("formFailure", dao.getBranchCode() + " already exists");
    		}
    		else {
    			redirectAttributes.addFlashAttribute("formFailure", e.getMessage());
    		}
    	}
    	
        return "redirect:/dao-form";
    }
    
    @GetMapping("/dao-grid")
	public String daoGrid() {
    	return "parameters/dao/daoGrid";
    }

}
