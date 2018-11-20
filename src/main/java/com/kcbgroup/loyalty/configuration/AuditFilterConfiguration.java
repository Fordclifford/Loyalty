package com.kcbgroup.loyalty.configuration;

import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.actuate.trace.http.HttpExchangeTracer;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.web.trace.servlet.HttpTraceFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

@Component
public class AuditFilterConfiguration extends HttpTraceFilter {
	
	private static final String[] EXCLUSIONS = 
			new String[]{"/css/**", "/js/**", "/images/**", "/webjars/**", "/actuator/**", "/ws/**"};

	public AuditFilterConfiguration(HttpTraceRepository repository, HttpExchangeTracer tracer) {
		super(repository, tracer);
	}
	
	@Override
    protected boolean shouldNotFilter(final HttpServletRequest request) throws ServletException {
        return Arrays.stream(EXCLUSIONS).anyMatch(e -> new AntPathMatcher().match(e, request.getServletPath()));
    }

}
