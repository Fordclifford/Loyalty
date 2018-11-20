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

import com.kcbgroup.loyalty.model.ProductCode;
import com.kcbgroup.loyalty.repository.ProductCodeRepository;

@Controller
public class ProductCodeController {
    
	@Autowired
    private ProductCodeRepository productCodeRepository;
    
    @ResponseBody
	@GetMapping("/list-product-code-grid")
	public ResponseEntity<List<ProductCode>> listProductCodeGrid() {
		List<ProductCode> productCodes = productCodeRepository.findAll();
		return ResponseEntity.ok(productCodes);
	}
    
    @Transactional
    @PostMapping("/edit-product-code-form")
    public String editProductCodeForm(@ModelAttribute ProductCode productCode, HttpServletRequest request, 
    		RedirectAttributes redirectAttributes) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			ProductCode productCodeEdit = productCodeRepository.getOne(id);
			
			productCodeEdit.setCode(productCode.getCode());
			productCodeEdit.setDescription(productCode.getDescription());
			productCodeRepository.save(productCodeEdit);
			redirectAttributes.addFlashAttribute("gridSuccess", "Product code edited successfully");
    	}
    	catch(Exception e) {
    		redirectAttributes.addFlashAttribute("gridFailure", e.getMessage());
    	}
    	
        return "redirect:/product-code-grid";
    }
    
    @ResponseBody
    @DeleteMapping("/delete-product-code/{id}")
	public ResponseEntity<String> deleteProductCode(@PathVariable Long id) {
		try {
			productCodeRepository.deleteById(id);
			return ResponseEntity.ok("Product code deleted successfully");
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
	}
	
	@GetMapping("/product-code-form")
    public String getProductCodeForm(Model model) {
        model.addAttribute("productCode", new ProductCode());
        return "parameters/product-code/productCodeForm";
    }

    @PostMapping("/product-code-form")
    public String postProductCodeForm(@ModelAttribute ProductCode productCode, RedirectAttributes redirectAttributes) {
		try {
			ProductCode productCodeNew = new ProductCode();
			productCodeNew.setCode(productCode.getCode());
			productCodeNew.setDescription(productCode.getDescription());
			productCodeRepository.save(productCodeNew);
			redirectAttributes.addFlashAttribute("formSuccess", "Product code captured successfully");
    	}
    	catch(Exception e) {
    		redirectAttributes.addFlashAttribute("formFailure", e.getMessage());
    	}
    	
        return "redirect:/product-code-form";
    }
    
    @GetMapping("/product-code-grid")
	public String productCodeGrid() {
    	return "parameters/product-code/productCodeGrid";
    }

}
