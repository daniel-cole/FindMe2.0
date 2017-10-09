package com.findme.security;

import java.io.IOException;

import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private final Logger logger = LoggerFactory.getLogger(JWTLoginFilter.class);

	private TokenAuthenticationService tokenAuthenticationService;

	public JWTLoginFilter(String url, AuthenticationManager authManager,
			TokenAuthenticationService tokenAuthenticationService) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		this.tokenAuthenticationService = tokenAuthenticationService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {

		AccountCredentials creds;

		try {
			creds = new ObjectMapper().readValue(req.getInputStream(), AccountCredentials.class);
			logger.debug("successfully parsed payload for login");
		} catch (com.fasterxml.jackson.databind.JsonMappingException e) {
			logger.debug("bad payload provided for login");
			creds = new AccountCredentials();
		}

		logger.debug(creds.getUsername() + creds.getPassword());

		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
				creds.getPassword(), Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		tokenAuthenticationService.addAuthentication(res, auth.getName());
	}
}