package com.findme.security;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.findme.model.User;
import com.findme.service.UserService;

public class CustomAuthenticationManager implements AuthenticationManager {

	private final Logger logger = LoggerFactory.getLogger(CustomAuthenticationManager.class);

	private UserService userService;
	private SecurityHelper securityHelper;

	public CustomAuthenticationManager(UserService userService, SecurityHelper securityHelper) {
		super();
		this.userService = userService;
		this.securityHelper = securityHelper;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		// cast to string

		String username = authentication.getPrincipal() + "";
		String password = authentication.getCredentials() + "";

		logger.debug(String.format("authenticating user '%s' using custom authentication manager", username));

		User user = userService.getUser(username);
		if (user == null) {
			throw new BadCredentialsException("Invalid username or password provided");
		}

		if (!user.getEnabled()) {
			throw new DisabledException(String.format("User: '%s' is currently disabled.", username));
		}

		try {
			if (!securityHelper.comparePassword(password, user.getPassword())) {
				throw new BadCredentialsException("Invalid username or password provided");
			}
		} catch (IllegalArgumentException e) {
			// if salt doesn't match password; error will get thrown
			e.printStackTrace();
			throw new AuthenticationServiceException(
					String.format("Something went wrong when checking '%s' password", username));
		}

		return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());

		// TODO: get grants for user. i.e.
		// List<GrantedAuthority> userRights = rightsRepo.getUserRights(username);
	}

}