package com.kcbgroup.loyalty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcbgroup.loyalty.model.TransSummary;
import com.kcbgroup.loyalty.repository.TransSummaryRepository;

@Controller
public class TransSummaryController {
	
	@Autowired
	private TransSummaryRepository transSummaryRepository;
	
	@ResponseBody
	@GetMapping("/list-trans-summary-grid")
	public ResponseEntity<List<TransSummary>> listTransSummaryGrid() {
		List<TransSummary> transSummary = transSummaryRepository.findAll();
		return ResponseEntity.ok(transSummary);
	}
    
    @GetMapping("/trans-summary")
	public String transSummary() {
    	return "summaries/trans-summary/transSummary";
    }

}
