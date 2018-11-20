package com.kcbgroup.loyalty.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kcbgroup.loyalty.model.Directory;
import com.kcbgroup.loyalty.repository.DirectoryRepository;

@Controller
public class DirectoryController {
	
	private static final String GRID_FAILURE = "gridFailure";
	private static final String EXISTS = " already exists";
	
	@Autowired
	private DirectoryRepository directoryRepository;
	
	@ResponseBody
	@GetMapping("/list-directory-grid")
	public ResponseEntity<List<Directory>> listDirectoryGrid() {
		List<Directory> directories = directoryRepository.findAll();
		return ResponseEntity.ok(directories);
	}
    
    @Transactional
    @PostMapping("/edit-directory-form")
    public String editDirectoryForm(@ModelAttribute Directory directory, HttpServletRequest request, 
    		RedirectAttributes redirectAttributes) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			Directory directoryEdit = directoryRepository.getOne(id);
			
			directoryEdit.setKcbInDir(directory.getKcbInDir());
			directoryEdit.setKcbOutDir(directory.getKcbOutDir());
			directoryEdit.setInfiniaInDir(directory.getInfiniaInDir());
			directoryEdit.setInfiniaOutDir(directory.getInfiniaOutDir());
			directoryEdit.setCustomerInDir(directory.getCustomerInDir());
			directoryEdit.setCustomerOutDir(directory.getCustomerOutDir());
			directoryEdit.setKcbBkDir(directory.getKcbBkDir());
			directoryEdit.setInfiniaBkDir(directory.getInfiniaBkDir());
			directoryEdit.setCustomerBkDir(directory.getCustomerBkDir());
			directoryRepository.save(directoryEdit);
			redirectAttributes.addFlashAttribute("gridSuccess", "Directory edited successfully");
    	}
    	catch(Exception e) {
    		if (e.getMessage().contains("DIRECTORY_UK1")) {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, directory.getKcbInDir() + EXISTS);
    		}
    		else if (e.getMessage().contains("DIRECTORY_UK2")) {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, directory.getKcbOutDir() + EXISTS);
    		}
    		else if (e.getMessage().contains("DIRECTORY_UK3")) {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, directory.getInfiniaInDir() + EXISTS);
    		}
    		else if (e.getMessage().contains("DIRECTORY_UK4")) {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, directory.getInfiniaOutDir() + EXISTS);
    		}
    		else if (e.getMessage().contains("DIRECTORY_UK5")) {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, directory.getCustomerInDir() + EXISTS);
    		}
    		else if (e.getMessage().contains("DIRECTORY_UK6")) {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, directory.getCustomerOutDir() + EXISTS);
    		}
    		else if (e.getMessage().contains("DIRECTORY_UK7")) {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, directory.getKcbBkDir() + EXISTS);
    		}
    		else if (e.getMessage().contains("DIRECTORY_UK8")) {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, directory.getInfiniaBkDir() + EXISTS);
    		}
    		else if (e.getMessage().contains("DIRECTORY_UK9")) {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, directory.getCustomerBkDir() + EXISTS);
    		}
    		else {
    			redirectAttributes.addFlashAttribute(GRID_FAILURE, e.getMessage());
    		}
    	}
    	
        return "redirect:/directory-grid";
    }
    
    @GetMapping("/directory-grid")
	public String directoryGrid() {
    	return "parameters/directory/directoryGrid";
    }

}
