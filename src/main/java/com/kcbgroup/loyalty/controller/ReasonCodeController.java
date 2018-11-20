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

import com.kcbgroup.loyalty.model.ReasonCode;
import com.kcbgroup.loyalty.repository.ReasonCodeRepository;

@Controller
public class ReasonCodeController {
    
	@Autowired
    private ReasonCodeRepository reasonCodeRepository;
    
    @ResponseBody
	@GetMapping("/list-reason-code-grid")
	public ResponseEntity<List<ReasonCode>> listReasonCodeGrid() {
		List<ReasonCode> reasonCodes = reasonCodeRepository.findAll();
		return ResponseEntity.ok(reasonCodes);
	}
    
    @Transactional
    @PostMapping("/edit-reason-code-form")
    public String editReasonCodeForm(@ModelAttribute ReasonCode reasonCode, HttpServletRequest request, 
    		RedirectAttributes redirectAttributes) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			ReasonCode reasonCodeEdit = reasonCodeRepository.getOne(id);
			
			reasonCodeEdit.setCode(reasonCode.getCode());
			reasonCodeEdit.setDescription(reasonCode.getDescription());
			reasonCodeRepository.save(reasonCodeEdit);
			redirectAttributes.addFlashAttribute("gridSuccess", "Reason code edited successfully");
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("REASONCODES_UK1")) {
    			redirectAttributes.addFlashAttribute("gridFailure", reasonCode.getCode() + " already exists");
    		}
    		else {
    			redirectAttributes.addFlashAttribute("gridFailure", e.getMessage());
    		}
    	}
    	
        return "redirect:/reason-code-grid";
    }
    
    @ResponseBody
    @DeleteMapping("/delete-reason-code/{id}")
	public ResponseEntity<String> deleteReasonCode(@PathVariable Long id) {
		try {
			reasonCodeRepository.deleteById(id);
			return ResponseEntity.ok("Reason code deleted successfully");
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
	}
	
	@GetMapping("/reason-code-form")
    public String getReasonCodeForm(Model model) {
        model.addAttribute("reasonCode", new ReasonCode());
        return "parameters/reason-code/reasonCodeForm";
    }

    @PostMapping("/reason-code-form")
    public String postReasonCodeForm(@ModelAttribute ReasonCode reasonCode, 
    		RedirectAttributes redirectAttributes) {
		try {
			ReasonCode reasonCodeNew = new ReasonCode();
			reasonCodeNew.setCode(reasonCode.getCode());
			reasonCodeNew.setDescription(reasonCode.getDescription());
			reasonCodeRepository.save(reasonCodeNew);
			redirectAttributes.addFlashAttribute("formSuccess", "Reason code captured successfully");
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("REASONCODES_UK1")) {
    			redirectAttributes.addFlashAttribute("formFailure", reasonCode.getCode() + " already exists");
    		}
    		else {
    			redirectAttributes.addFlashAttribute("formFailure", e.getMessage());
    		}
    	}
    	
        return "redirect:/reason-code-form";
    }
    
    @GetMapping("/reason-code-grid")
	public String reasonCodeGrid() {
    	return "parameters/reason-code/reasonCodeGrid";
    }

}
