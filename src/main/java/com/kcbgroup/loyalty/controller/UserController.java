package com.kcbgroup.loyalty.controller;

import java.util.List;
import java.util.Optional;

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
import com.kcbgroup.loyalty.model.Role;
import com.kcbgroup.loyalty.model.User;
import com.kcbgroup.loyalty.repository.DaoRepository;
import com.kcbgroup.loyalty.repository.RoleRepository;
import com.kcbgroup.loyalty.repository.UserRepository;
import com.kcbgroup.loyalty.user.CustomUserDetailsService;

@Controller
public class UserController {
	
	private static final String GRID_FAILURE = "gridFailure";
	private static final String FORM_FAILURE = "formFailure";
	private static final String EXISTS = " already exists";
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private DaoRepository daoRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@ResponseBody
	@GetMapping("/list-user-grid")
	public ResponseEntity<List<User>> listUserGrid() {
		List<User> users = userRepository.findAll();
		users.forEach(user -> {
			Optional<Dao> dao = daoRepository.findByBranchCode(user.getDao());
			if (dao.isPresent()) user.setDao(dao.get().getBranchName());
		});
		return ResponseEntity.ok(users);
	}
    
	@Transactional
    @PostMapping("/edit-user-form")
    public String editUserForm(@ModelAttribute User user, HttpServletRequest request, 
    		RedirectAttributes redirectAttributes) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			userDetailsService.editUser(id, user);
    		redirectAttributes.addFlashAttribute("gridSuccess", "User edited successfully");
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("USERS_UK1")) {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, user.getUsername() + EXISTS);
    		}
    		else if (e.getMessage().contains("USERS_UK2")) {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, user.getEmail() + EXISTS);
    		}
    		else {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, e.getMessage());
    		}
    	}
    	
        return "redirect:/user-grid";
    }
    
    @ResponseBody
    @DeleteMapping("/delete-user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    	try {
    		userRepository.deleteById(id);
    		return ResponseEntity.ok("User deleted successfully");
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
	}
	
	@GetMapping("/user-form")
    public String getUserForm(Model model) {
		List<Role> roleList = roleRepository.findAll();
		List<Dao> daoList = daoRepository.findAll();
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleList);
        model.addAttribute("dao", daoList);
        return "access-control/user/userForm";
    }

    @PostMapping("/user-form")
    public String postUserForm(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
    	try {
    		userDetailsService.newUser(user);
    		redirectAttributes.addFlashAttribute("formSuccess", "User captured successfully");
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("USERS_UK1")) {
    			redirectAttributes.addFlashAttribute(FORM_FAILURE, user.getUsername() + EXISTS);
    		}
    		else if (e.getMessage().contains("USERS_UK2")) {
    			redirectAttributes.addFlashAttribute(FORM_FAILURE, user.getEmail() + EXISTS);
    		}
    		else {
    			redirectAttributes.addFlashAttribute(FORM_FAILURE, e.getMessage());
    		}
    	}
    	
        return "redirect:/user-form";
    }
    
    @GetMapping("/user-grid")
	public String userGrid(Model model) {
    	List<Role> roleList = roleRepository.findAll();
    	List<Dao> daoList = daoRepository.findAll();
    	model.addAttribute("roles", roleList);
    	model.addAttribute("dao", daoList);
    	return "access-control/user/userGrid";
    }

}
