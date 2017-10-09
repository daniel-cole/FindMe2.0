package com.findme.service;

import com.findme.exception.ResourceNotFoundException;
import com.findme.exception.UnknownServerException;
import com.findme.model.User;
import com.findme.model.UserImage;

public interface UserImageService {
	public String getUserImage(String username) throws ResourceNotFoundException;

	UserImage createUserImage(User user, String imageUrl) throws UnknownServerException;
}
