package com.kcbgroup.loyalty.controller;

import java.util.ArrayList;
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

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.kcbgroup.loyalty.model.Channel;
import com.kcbgroup.loyalty.model.ProductCode;
import com.kcbgroup.loyalty.repository.ProductCodeRepository;
import com.kcbgroup.loyalty.repository.ChannelRepository;

@Controller
public class ChannelController {
	
	@Autowired
	private ChannelRepository channelRepository;
	
	@Autowired
	private ProductCodeRepository productCodeRepository;
    
    @ResponseBody
	@GetMapping("/list-channel-grid")
	public ResponseEntity<List<Channel>> listChannelGrid() {
		List<Channel> channels = channelRepository.findAll();
		return ResponseEntity.ok(channels);
	}
    
    @ResponseBody
    @GetMapping("/code-categories/{code}")
	public ResponseEntity<List<String>> codeCategories(@PathVariable String code) {
    	List<ProductCode> productCodes = productCodeRepository.findByCode(code);
    	List<String> codeCategories = new ArrayList<>();
    	productCodes.forEach(productCode -> codeCategories.add(productCode.getDescription()));
		return ResponseEntity.ok(codeCategories);
	}
    
    @Transactional
    @PostMapping("/edit-channel-form")
    public String editChannelForm(@ModelAttribute Channel channel, HttpServletRequest request, 
    		RedirectAttributes redirectAttributes) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			Channel channelEdit = channelRepository.getOne(id);
			
			channelEdit.setCode(channel.getCode());
			channelEdit.setCategory(channel.getCategory());
			channelEdit.setSubCategory(channel.getSubCategory());
			channelRepository.save(channelEdit);
			redirectAttributes.addFlashAttribute("gridSuccess", "Channel edited successfully");
    	}
    	catch(Exception e) {
    		redirectAttributes.addFlashAttribute("gridFailure", e.getMessage());
    	}
    	
        return "redirect:/channel-grid";
    }
    
    @ResponseBody
    @DeleteMapping("/delete-channel/{id}")
	public ResponseEntity<String> deleteChannel(@PathVariable Long id) {
		try {
			channelRepository.deleteById(id);
			return ResponseEntity.ok("Channel deleted successfully");
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
	}
	
	@GetMapping("/channel-form")
    public String getChannelForm(Model model) {
		List<ProductCode> codeList = productCodeRepository.findAll();
		ListMultimap<String, String> codeMap = ArrayListMultimap.create();
		
		codeList.forEach(record -> codeMap.put(record.getCode(), record.getDescription()));
		model.addAttribute("codes", codeMap.keySet());
        model.addAttribute("channel", new Channel());
        return "parameters/channel/channelForm";
    }

    @PostMapping("/channel-form")
    public String postChannelForm(@ModelAttribute Channel channel, RedirectAttributes redirectAttributes) {
		try {
			Channel channelNew = new Channel();
			channelNew.setCode(channel.getCode());
			channelNew.setCategory(channel.getCategory());
			channelNew.setSubCategory(channel.getSubCategory());
	    	channelRepository.save(channelNew);
			redirectAttributes.addFlashAttribute("formSuccess", "Channel captured successfully");
    	}
    	catch(Exception e) {
    		redirectAttributes.addFlashAttribute("formFailure", e.getMessage());
    	}
    	
        return "redirect:/channel-form";
    }
    
    @GetMapping("/channel-grid")
	public String channelGrid(Model model) {
    	List<ProductCode> codeList = productCodeRepository.findAll();
		ListMultimap<String, String> codeMap = ArrayListMultimap.create();
		
		codeList.forEach(record -> codeMap.put(record.getCode(), record.getDescription()));
		model.addAttribute("codes", codeMap.keySet());
    	return "parameters/channel/channelGrid";
    }

}
