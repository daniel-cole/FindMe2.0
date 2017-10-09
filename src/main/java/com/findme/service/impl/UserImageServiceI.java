package com.findme.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.findme.exception.ResourceNotFoundException;
import com.findme.exception.UnknownServerException;
import com.findme.model.User;
import com.findme.model.UserImage;
import com.findme.repository.UserImageRepository;
import com.findme.service.UserImageService;

@Service
@Transactional
public class UserImageServiceI implements UserImageService {

	@Autowired
	private UserImageRepository userImageRepository;

	@Override
	@Cacheable
	public String getUserImage(String username) throws ResourceNotFoundException {
		String img = userImageRepository.findByUsername(username).getImageUrl();
		if (img == null) {
			throw new ResourceNotFoundException("Failed to find image for user: " + username);
		}
		return img;
	}

	@Override
	public UserImage createUserImage(User user, String imageUrl) throws UnknownServerException {
		UserImage userImage = userImageRepository.save(new UserImage(user, imageUrl));
		if (userImage == null) {
			throw new UnknownServerException(
					"failed to update database with new image for user: " + user.getUsername());
		}

		// OK
		return userImage;
	}

}
