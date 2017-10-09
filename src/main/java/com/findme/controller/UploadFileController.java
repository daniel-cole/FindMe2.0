package com.findme.controller;

import java.io.IOException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.findme.exception.BadRequestException;
import com.findme.exception.UnknownServerException;
import com.findme.exception.UploadErrorException;
import com.findme.model.User;
import com.findme.service.UserImageService;
import com.findme.service.UserService;
import com.findme.utils.MessageUtility;
import com.findme.utils.S3Client;

@RestController
public class UploadFileController {

	private final Logger logger = LoggerFactory.getLogger(UploadFileController.class);

	@Autowired
	private S3Client s3Client;

	@Autowired
	private MessageUtility messageUtility;

	@Autowired
	private UserImageService userImageService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/api/upload", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> upload(@RequestParam("name") String filename,
			@RequestParam("file") MultipartFile file, Authentication authentication) throws UnknownServerException,
			UploadErrorException, AuthenticationException, IOException, Error, BadRequestException {

		String username = authentication.getName();
		String artifactUrl = uploadFile(filename, file, username);

		setUserImage(username, artifactUrl);

		String response = messageUtility.createResponseMessage("artifactUrl", artifactUrl);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	private void setUserImage(String username, String imageUrl) throws UnknownServerException {
		User user = userService.getUser(username);
		userImageService.createUserImage(user, imageUrl);
	}

	private String uploadFile(String filename, MultipartFile file, String username) throws BadRequestException {
		logger.debug(String.format("Attempting to upload file for user: %s", username));

		// set name of the upload
		filename += "_" + username;

		URL artifactUrl = null;

		artifactUrl = s3Client.uploadImageToBucket(filename, file);

		return artifactUrl.toString();
	}
}
