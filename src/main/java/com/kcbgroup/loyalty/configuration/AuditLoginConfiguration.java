package com.kcbgroup.loyalty.configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.kcbgroup.loyalty.model.AuditTrail;
import com.kcbgroup.loyalty.repository.AuditTrailRepository;

@Component
public class AuditLoginConfiguration {
	
	@Autowired
	private AuditTrailRepository auditTrailRepository;
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Autowired
	private HttpServletResponse httpServletResponse;
	
	@EventListener
    public void auditLogin(AuditApplicationEvent auditApplicationEvent) {
        AuditEvent auditEvent = auditApplicationEvent.getAuditEvent();
        AuditTrail auditTrail = new AuditTrail();
        
        try {
			String header = httpServletRequest.getHeader("referer");
        	
        	if (header != null) {
				URI uri = new URI(header);
				String referrer = uri.getPath().substring(httpServletRequest.getContextPath().length());
				auditTrail.setReferrer(referrer);
        	}
		} 
		catch (URISyntaxException e) {
			e.getMessage();
		}
		
		auditTrail.setTimestamp(LocalDateTime.ofInstant(auditEvent.getTimestamp(), ZoneId.systemDefault()));
		auditTrail.setPrincipal(auditEvent.getPrincipal());
		auditTrail.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		auditTrail.setMethod(httpServletRequest.getMethod());
		auditTrail.setUri(httpServletRequest.getServletPath());
		auditTrail.setHost(httpServletRequest.getHeader("host"));
		auditTrail.setCookie(httpServletRequest.getHeader("cookie"));
		auditTrail.setRemoteAddress(httpServletRequest.getRemoteAddr());
		auditTrail.setStatus(httpServletResponse.getStatus());
		auditTrail.setType(auditEvent.getType());
		auditTrail.setMessage((String) auditEvent.getData().get("message"));
		auditTrailRepository.save(auditTrail);
    }

}
