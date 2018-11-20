package com.kcbgroup.loyalty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcbgroup.loyalty.model.AuditTrail;
import com.kcbgroup.loyalty.repository.AuditTrailRepository;

@Controller
public class AuditTrailController {
    
	@Autowired
    private AuditTrailRepository auditTrailRepository;
    
    @ResponseBody
	@GetMapping("/list-audit-trail-grid")
	public DataTablesOutput<AuditTrail> auditGrid(DataTablesInput input) {
    	return auditTrailRepository.findAll(input);
	}
    
    @GetMapping("/audit-trail")
	public String auditTrail() {
    	return "audit-trail/auditTrail";
    }

}
