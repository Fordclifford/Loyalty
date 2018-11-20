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

import com.kcbgroup.loyalty.model.Currency;
import com.kcbgroup.loyalty.repository.CurrencyRepository;

@Controller
public class CurrencyController {
	
	private static final String GRID_FAILURE = "gridFailure";
	private static final String FORM_FAILURE = "formFailure";
	private static final String EXISTS = " already exists";
    
	@Autowired
    private CurrencyRepository currencyRepository;
    
    @ResponseBody
	@GetMapping("/list-currency-grid")
	public ResponseEntity<List<Currency>> listCurrencyGrid() {
		List<Currency> currencies = currencyRepository.findAll();
		return ResponseEntity.ok(currencies);
	}
    
    @Transactional
    @PostMapping("/edit-currency-form")
    public String editCurrencyForm(@ModelAttribute Currency currency, HttpServletRequest request, 
    		RedirectAttributes redirectAttributes) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			Currency currencyEdit = currencyRepository.getOne(id);
			
			currencyEdit.setCodeNumber(currency.getCodeNumber());
			currencyEdit.setAbbreviation(currency.getAbbreviation());
			currencyEdit.setName(currency.getName());
			currencyRepository.save(currencyEdit);
			redirectAttributes.addFlashAttribute("gridSuccess", "Currency edited successfully");
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("CURRENCY_UK1")) {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, currency.getCodeNumber() + EXISTS);
    		}
    		else if (e.getMessage().contains("CURRENCY_UK2")) {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, currency.getAbbreviation() + EXISTS);
    		}
    		else if (e.getMessage().contains("CURRENCY_UK3")) {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, currency.getName() + EXISTS);
    		}
    		else {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, e.getMessage());
    		}
    	}
    	
        return "redirect:/currency-grid";
    }
    
    @ResponseBody
    @DeleteMapping("/delete-currency/{id}")
	public ResponseEntity<String> deleteCurrency(@PathVariable Long id) {
		try {
			currencyRepository.deleteById(id);	
			return ResponseEntity.ok("Currency deleted successfully");
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
	}
	
	@GetMapping("/currency-form")
    public String getCurrencyForm(Model model) {
        model.addAttribute("currency", new Currency());
        return "parameters/currency/currencyForm";
    }

    @PostMapping("/currency-form")
    public String postCurrencyForm(@ModelAttribute Currency currency, RedirectAttributes redirectAttributes) {
		try {
			Currency currencyNew = new Currency();
			currencyNew.setCodeNumber(currency.getCodeNumber());
			currencyNew.setAbbreviation(currency.getAbbreviation());
			currencyNew.setName(currency.getName());
			currencyRepository.save(currencyNew);
			redirectAttributes.addFlashAttribute("formSuccess", "Currency captured successfully");
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("CURRENCY_UK1")) {
    			redirectAttributes.addFlashAttribute(FORM_FAILURE, currency.getCodeNumber() + EXISTS);
    		}
    		else if (e.getMessage().contains("CURRENCY_UK2")) {
    			redirectAttributes.addFlashAttribute(FORM_FAILURE, currency.getAbbreviation() + EXISTS);
    		}
    		else if (e.getMessage().contains("CURRENCY_UK3")) {
    			redirectAttributes.addFlashAttribute(FORM_FAILURE, currency.getName() + EXISTS);
    		}
    		else {
    			redirectAttributes.addFlashAttribute(FORM_FAILURE, e.getMessage());
    		}
    	}
    	
        return "redirect:/currency-form";
    }
    
    @GetMapping("/currency-grid")
	public String currencyGrid() {
    	return "parameters/currency/currencyGrid";
    }

}
