package com.findme.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.findme.exception.DuplicateRecordException;
import com.findme.exception.ResourceNotFoundException;
import com.findme.model.User;
import com.findme.repository.UserRepository;
import com.findme.security.SecurityHelper;
import com.findme.service.UserService;

@Service
@Transactional
public class UserServiceI implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SecurityHelper securityHelper;

	@Transactional(readOnly = true)
	public List<User> getUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User createUser(String username, String password, String email) throws DuplicateRecordException {
		if (userRepository.existsByEmail(email)) {
			throw new DuplicateRecordException(String.format("email '%s' already exists", email));
		}

		if (userRepository.existsByUsername(username)) {
			throw new DuplicateRecordException(String.format("username '%s' already exists", username));
		}

		return userRepository.save(new User(username, securityHelper.hashPassword(password), email, true));
	}

	@Override
	public void disableUser(String username) throws ResourceNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new ResourceNotFoundException("user does not exist: " + username);
		}
		user.setEnabled(false);
		userRepository.save(user);
	}

	@Override
	public void enableUser(String username) throws ResourceNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new ResourceNotFoundException("user does not exist: " + username);
		}
		user.setEnabled(true);
		userRepository.save(user);
	}

}