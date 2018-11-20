package com.kcbgroup.loyalty.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.kcbgroup.loyalty.model.Role;
import com.kcbgroup.loyalty.repository.PrivilegeRepository;
import com.kcbgroup.loyalty.repository.RoleRepository;
import com.kcbgroup.loyalty.repository.UserRepository;

@Controller
public class RoleController {
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@ResponseBody
	@GetMapping("/list-role-grid")
	public ResponseEntity<List<Role>> listRoleGrid() {
		List<Role> roles = roleRepository.findAll();
		roles.forEach(role -> {
			role.setPrivileges(role.getPrivileges().replaceAll(",", ", "));
			List<String> users = new ArrayList<>();
			userRepository.findByRole(role.getName()).forEach(user -> users.add(user.getUsername()));
			role.setUsers(StringUtils.strip(users.toString(), "[]"));
		});
		return ResponseEntity.ok(roles);
	}
    
	@Transactional
    @PostMapping("/edit-role-form")
    public String editRoleForm(@ModelAttribute Role role, HttpServletRequest request, 
    		RedirectAttributes redirectAttributes) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			Role roleEdit = roleRepository.getOne(id);
			
			roleEdit.setName(role.getName().toUpperCase().replace(' ', '_'));
			roleEdit.setPrivileges(role.getPrivileges().toUpperCase().replace(' ', '_'));
			roleRepository.save(roleEdit);
			redirectAttributes.addFlashAttribute("gridSuccess", "Role edited successfully");
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("ROLES_UK1")) {
    			redirectAttributes.addFlashAttribute("gridFailure", role.getName() + " already exists");
    		}
    		else {
    			redirectAttributes.addFlashAttribute("gridFailure", e.getMessage());
    		}
    	}
    	
        return "redirect:/role-grid";
    }
    
    @ResponseBody
    @DeleteMapping("/delete-role/{id}")
	public ResponseEntity<String> deleteRole(@PathVariable Long id) {
		try {
			roleRepository.deleteById(id);
			return ResponseEntity.ok("Role deleted successfully");
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
	}
	
	@GetMapping("/role-form")
    public String getRoleForm(Model model) {
		List<Privilege> privilegeList = privilegeRepository.findAll();
        model.addAttribute("role", new Role());
        model.addAttribute("privileges", privilegeList);
        return "access-control/role/roleForm";
    }

    @PostMapping("/role-form")
    public String postRoleForm(@ModelAttribute Role role, RedirectAttributes redirectAttributes) {
		try {
			Role roleNew = new Role();
			roleNew.setName(role.getName().toUpperCase().replace(' ', '_'));
			roleNew.setPrivileges(role.getPrivileges().toUpperCase().replace(' ', '_'));
	    	roleRepository.save(roleNew);
			redirectAttributes.addFlashAttribute("formSuccess", "Role captured successfully");
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("ROLES_UK1")) {
    			redirectAttributes.addFlashAttribute("formFailure", role.getName() + " already exists");
    		}
    		else {
    			redirectAttributes.addFlashAttribute("formFailure", e.getMessage());
    		}
    	}
    	
        return "redirect:/role-form";
    }
    
    @GetMapping("/role-grid")
	public String roleGrid(Model model) {
    	List<Privilege> privilegeList = privilegeRepository.findAll();
    	model.addAttribute("privileges", privilegeList);
    	return "access-control/role/roleGrid";
    }

}
