package com.findme.security;

import static java.util.Collections.emptyList;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.findme.model.User;
import com.findme.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenAuthenticationService {

	private final Logger logger = LoggerFactory.getLogger(TokenAuthenticationService.class);

	@Autowired
	private UserService userService;

	private final long EXPIRATION_TIME = 86400_000; // 1 day

	private final String TOKEN_PREFIX = "Bearer";
	private final String HEADER_STRING = "Authorization";

	@Value("${jwt.secret}")
	private String SECRET;

	public void addAuthentication(HttpServletResponse res, String username) {
		String JWT = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		logger.debug(String.format("filtering request to login for user; token is: %s", token));
		if (token != null) {
			// parse the token.
			String username = null;
			username = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
					.getSubject();

			// make sure user hasn't been disabled
			User user = userService.getUser(username);
			if (user == null) {
				logger.warn(String.format("received valid token but user '%s' does not exist", username));
				return null;
			}
			if (!user.getEnabled()) {
				logger.info(String.format("disabled user '%s' attempted to login", username));
				return null;
			}

			return username != null ? new UsernamePasswordAuthenticationToken(username, null, emptyList()) : null;
		}
		return null;
	}
}