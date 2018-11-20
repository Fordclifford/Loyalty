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

import com.kcbgroup.loyalty.model.Merchant;
import com.kcbgroup.loyalty.repository.MerchantRepository;

@Controller
public class MerchantController {
	
	private static final String GRID_FAILURE = "gridFailure";
	private static final String FORM_FAILURE = "formFailure";
	private static final String EXISTS = " already exists";
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@ResponseBody
	@GetMapping("/list-merchant-grid")
	public ResponseEntity<List<Merchant>> listMerchantGrid() {
		List<Merchant> merchants = merchantRepository.findAll();
		return ResponseEntity.ok(merchants);
	}
    
    @Transactional
    @PostMapping("/edit-merchant-form")
    public String editMerchantForm(@ModelAttribute Merchant merchant, HttpServletRequest request, 
    		RedirectAttributes redirectAttributes) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			Merchant merchantEdit = merchantRepository.getOne(id);
			
			merchantEdit.setmId(merchant.getmId());
			merchantEdit.setCode(merchant.getCode());
			merchantEdit.setName(merchant.getName());
			merchantEdit.setPassword(merchant.getPassword());
			merchantRepository.save(merchantEdit);
			redirectAttributes.addFlashAttribute("gridSuccess", "Merchant edited successfully");
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("MERCHANTS_UK1")) {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, merchant.getmId() + EXISTS);
    		}
    		else if (e.getMessage().contains("MERCHANTS_UK2")) {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, merchant.getCode() + EXISTS);
    		}
    		else if (e.getMessage().contains("MERCHANTS_UK3")) {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, merchant.getName() + EXISTS);
    		}
    		else {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, e.getMessage());
    		}
    	}
    	
        return "redirect:/merchant-grid";
    }
    
    @ResponseBody
    @DeleteMapping("/delete-merchant/{id}")
	public ResponseEntity<String> deleteMerchant(@PathVariable Long id) {
		try {
			merchantRepository.deleteById(id);
			return ResponseEntity.ok("Merchant deleted successfully");
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
	}
	
	@GetMapping("/merchant-form")
    public String getMerchantForm(Model model) {
        model.addAttribute("merchant", new Merchant());
        return "parameters/merchant/merchantForm";
    }

    @PostMapping("/merchant-form")
    public String postMerchantForm(@ModelAttribute Merchant merchant, RedirectAttributes redirectAttributes) {
		try {
			Merchant merchantNew = new Merchant();
			merchantNew.setmId(merchant.getmId());
			merchantNew.setCode(merchant.getCode());
			merchantNew.setName(merchant.getName());
			merchantNew.setPassword(merchant.getPassword());
			merchantRepository.save(merchantNew);
			redirectAttributes.addFlashAttribute("formSuccess", "Merchant captured successfully");
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("MERCHANTS_UK1")) {
    			redirectAttributes.addFlashAttribute(FORM_FAILURE, merchant.getmId() + EXISTS);
    		}
    		else if (e.getMessage().contains("MERCHANTS_UK2")) {
    			redirectAttributes.addFlashAttribute(FORM_FAILURE, merchant.getCode() + EXISTS);
    		}
    		else if (e.getMessage().contains("MERCHANTS_UK3")) {
    			redirectAttributes.addFlashAttribute(FORM_FAILURE, merchant.getName() + EXISTS);
    		}
    		else {
    			redirectAttributes.addFlashAttribute(FORM_FAILURE, e.getMessage());
    		}
    	}
    	
        return "redirect:/merchant-form";
    }
    
    @GetMapping("/merchant-grid")
	public String merchantGrid() {
    	return "parameters/merchant/merchantGrid";
    }

}
