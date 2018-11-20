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

import com.kcbgroup.loyalty.model.TransactionCode;
import com.kcbgroup.loyalty.repository.TransactionCodeRepository;

@Controller
public class TransactionCodeController {
	
	@Autowired
	private TransactionCodeRepository transactionCodeRepository;
    
    @ResponseBody
	@GetMapping("/list-transaction-code-grid")
	public ResponseEntity<List<TransactionCode>> listTransactionCodeGrid() {
		List<TransactionCode> transactionCodes = transactionCodeRepository.findAll();
		return ResponseEntity.ok(transactionCodes);
	}
    
    @Transactional
    @PostMapping("/edit-transaction-code-form")
    public String editTransactionCodeForm(@ModelAttribute TransactionCode transactionCode, HttpServletRequest request, 
    		RedirectAttributes redirectAttributes) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			TransactionCode transactionCodeEdit = transactionCodeRepository.getOne(id);
			
			transactionCodeEdit.setCode(transactionCode.getCode());
			transactionCodeEdit.setDescription(transactionCode.getDescription());
			transactionCodeRepository.save(transactionCodeEdit);
			redirectAttributes.addFlashAttribute("gridSuccess", "Transaction code edited successfully");
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("TRANSCODES_UK1")) {
    			redirectAttributes.addFlashAttribute("gridFailure", transactionCode.getCode() + " already exists");
    		}
    		else {
    			redirectAttributes.addFlashAttribute("gridFailure", e.getMessage());
    		}
    	}
    	
        return "redirect:/transaction-code-grid";
    }
    
    @ResponseBody
    @DeleteMapping("/delete-transaction-code/{id}")
	public ResponseEntity<String> deleteTransactionCode(@PathVariable Long id) {
		try {
			transactionCodeRepository.deleteById(id);
    		return ResponseEntity.ok("Transaction code deleted successfully");
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
	}
	
	@GetMapping("/transaction-code-form")
    public String getTransactionCodeForm(Model model) {
        model.addAttribute("transactionCode", new TransactionCode());
        return "parameters/transaction-code/transactionCodeForm";
    }

    @PostMapping("/transaction-code-form")
    public String postTransactionCodeForm(@ModelAttribute TransactionCode transactionCode, 
    		RedirectAttributes redirectAttributes) {		
		try {
			TransactionCode transactionCodeNew = new TransactionCode();
			transactionCodeNew.setCode(transactionCode.getCode());
			transactionCodeNew.setDescription(transactionCode.getDescription());
			transactionCodeRepository.save(transactionCodeNew);
			redirectAttributes.addFlashAttribute("formSuccess", "Transaction code captured successfully");
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("TRANSCODES_UK1")) {
    			redirectAttributes.addFlashAttribute("formFailure", transactionCode.getCode() + " already exists");
    		}
    		else {
    			redirectAttributes.addFlashAttribute("formFailure", e.getMessage());
    		}
    	}
    	
        return "redirect:/transaction-code-form";
    }
    
    @GetMapping("/transaction-code-grid")
	public String transactionCodeGrid() {
    	return "parameters/transaction-code/transactionCodeGrid";
    }

}
