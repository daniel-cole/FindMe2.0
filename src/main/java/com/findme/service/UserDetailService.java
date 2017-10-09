package com.findme.service;

import com.findme.model.User;
import com.findme.model.UserDetail;
import com.findme.utils.UserRole;

public interface UserDetailService {
	String getAuthority(String username);

	UserDetail createUserDetail(User user, UserRole role);
}
