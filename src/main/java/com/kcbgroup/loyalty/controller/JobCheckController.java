package com.kcbgroup.loyalty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcbgroup.loyalty.model.JobCheck;
import com.kcbgroup.loyalty.repository.JobCheckRepository;

@Controller
public class JobCheckController {
	
	@Autowired
	private JobCheckRepository jobCheckRepository;
	
	@ResponseBody
	@GetMapping("/list-job-check-grid")
	public ResponseEntity<List<JobCheck>> listJobCheckGrid() {
		List<JobCheck> jobChecks = jobCheckRepository.findAll();
		return ResponseEntity.ok(jobChecks);
	}
    
    @GetMapping("/job-check")
	public String jobCheck() {
    	return "job-check/jobCheck";
    }

}
