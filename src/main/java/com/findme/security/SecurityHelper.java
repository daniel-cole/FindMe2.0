package com.findme.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class SecurityHelper {

	private final String salt = BCrypt.gensalt(10);

	public String hashPassword(String password) {
		return BCrypt.hashpw(password, salt);
	}

	public boolean comparePassword(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}

}
