package com.cts.security.form;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class MyLoginFilter extends AbstractAuthenticationProcessingFilter {

	private static final String DEFAULT_FILTER_PROCESSES_URL = "/user/login";
	
		
	public MyLoginFilter() {
		super(DEFAULT_FILTER_PROCESSES_URL);
	}
	
	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response){
		String uri = request.getRequestURI();
        int pathParamIndex = uri.indexOf('?');

        if (pathParamIndex > 0) {
            // strip everything after the first semi-colon
            uri = uri.substring(0, pathParamIndex);
        }

        if ("".equals(request.getContextPath())) {
            return uri.endsWith(DEFAULT_FILTER_PROCESSES_URL);
        }

        return uri.endsWith(request.getContextPath() + DEFAULT_FILTER_PROCESSES_URL);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException,
			IOException, ServletException {

		String username = request.getParameter("j_username");
		String password = request.getParameter("j_password");

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}

		username = username.trim();

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}
	
	protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		
		//if ifl user is not eligible to access the site based on the decrypt logic, redirect to proper error page
		super.doFilter(request, response, chain);
		
	}
}
