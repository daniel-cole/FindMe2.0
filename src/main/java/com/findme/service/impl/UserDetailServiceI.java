package com.findme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.findme.model.User;
import com.findme.model.UserDetail;
import com.findme.repository.UserDetailRepository;
import com.findme.service.UserDetailService;
import com.findme.utils.UserRole;

@Service
@Transactional
public class UserDetailServiceI implements UserDetailService {

	@Autowired
	private UserDetailRepository userDetailRepository;

	@Transactional(readOnly = true)
	public String getAuthority(String username) {

		return userDetailRepository.findByUsername(username).getRole();

	}

	@Override
	public UserDetail createUserDetail(User user, UserRole role) {
		return userDetailRepository.save(new UserDetail(user, role.toString()));
	}

}
