package com.kcbgroup.loyalty.configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.kcbgroup.loyalty.model.AuditTrail;
import com.kcbgroup.loyalty.repository.AuditTrailRepository;

@Component
public class AuditConfiguration implements HttpTraceRepository {
	
	private final HttpTraceRepository httpTraceRepository = new InMemoryHttpTraceRepository();
	
	@Autowired
	private AuditTrailRepository auditTrailRepository;
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Autowired
	private HttpServletResponse httpServletResponse;

	@Override
	public List<HttpTrace> findAll() {
		return httpTraceRepository.findAll();
	}

	@Override
	public void add(HttpTrace trace) {
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
		
		auditTrail.setTimestamp(LocalDateTime.ofInstant(trace.getTimestamp(), ZoneId.systemDefault()));
		auditTrail.setPrincipal(SecurityContextHolder.getContext().getAuthentication().getName());
		auditTrail.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
		auditTrail.setMethod(httpServletRequest.getMethod());
		auditTrail.setUri(httpServletRequest.getServletPath());
		auditTrail.setHost(httpServletRequest.getHeader("host"));
		auditTrail.setCookie(httpServletRequest.getHeader("cookie"));
		auditTrail.setRemoteAddress(httpServletRequest.getRemoteAddr());
		auditTrail.setStatus(httpServletResponse.getStatus());
		auditTrail.setTimeTaken(trace.getTimeTaken());
		auditTrailRepository.save(auditTrail);
		this.httpTraceRepository.add(trace);
	}

}
