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

import com.kcbgroup.loyalty.model.Privilege;
import com.kcbgroup.loyalty.repository.PrivilegeRepository;

@Controller
public class PrivilegeController {
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@ResponseBody
	@GetMapping("/list-privilege-grid")
	public ResponseEntity<List<Privilege>> listPrivilegeGrid() {
		List<Privilege> privileges = privilegeRepository.findAll();
		return ResponseEntity.ok(privileges);
	}
    
	@Transactional
    @PostMapping("/edit-privilege-form")
    public String editPrivilegeForm(@ModelAttribute Privilege privilege, HttpServletRequest request, 
    		RedirectAttributes redirectAttributes) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			Privilege privilegeEdit = privilegeRepository.getOne(id);
			
			privilegeEdit.setName(privilege.getName().toUpperCase().replace(' ', '_'));
			privilegeRepository.save(privilegeEdit);
			redirectAttributes.addFlashAttribute("gridSuccess", "Privilege edited successfully");
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("PRIVILEGE_UK1")) {
    			redirectAttributes.addFlashAttribute("gridFailure", privilege.getName() + " already exists");
    		}
    		else {
    			redirectAttributes.addFlashAttribute("gridFailure", e.getMessage());
    		}
    	}
    	
        return "redirect:/privilege-grid";
    }
    
    @ResponseBody
    @DeleteMapping("/delete-privilege/{id}")
	public ResponseEntity<String> deletePrivilege(@PathVariable Long id) {
		try {
			privilegeRepository.deleteById(id);
			return ResponseEntity.ok("Privilege deleted successfully");
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
	}
	
	@GetMapping("/privilege-form")
    public String getPrivilegeForm(Model model) {
        model.addAttribute("privilege", new Privilege());
        return "access-control/privilege/privilegeForm";
    }

    @PostMapping("/privilege-form")
    public String postPrivilegeForm(@ModelAttribute Privilege privilege, RedirectAttributes redirectAttributes) {
		try {
			Privilege privilegeNew = new Privilege();
			privilegeNew.setName(privilege.getName().toUpperCase().replace(' ', '_'));
	    	privilegeRepository.save(privilegeNew);
			redirectAttributes.addFlashAttribute("formSuccess", "Privilege captured successfully");
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("PRIVILEGE_UK1")) {
    			redirectAttributes.addFlashAttribute("formFailure", privilege.getName() + " already exists");
    		}
    		else {
    			redirectAttributes.addFlashAttribute("formFailure", e.getMessage());
    		}
    	}
    	
        return "redirect:/privilege-form";
    }
    
    @GetMapping("/privilege-grid")
	public String privilegeGrid() {
    	return "access-control/privilege/privilegeGrid";
    }

}
