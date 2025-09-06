package com.shorturl.shorturl.configuration;

import java.io.IOException;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@Component
@WebFilter(urlPatterns = "/*")
public class LogContextFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest httpRequest = (HttpServletRequest) request;

			String user = "annonymus";
			String ipAddress = request.getRemoteAddr();
			String apiEndpoint = httpRequest.getRequestURI();

			ThreadContext.put("user", user);
			ThreadContext.put("ipAddress", ipAddress);
			ThreadContext.push("apiEndpoint", apiEndpoint);

			chain.doFilter(request, response);
		} finally {
			ThreadContext.clearAll();
		}

	}
}
