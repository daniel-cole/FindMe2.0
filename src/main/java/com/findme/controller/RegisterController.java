package com.findme.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.findme.exception.BadRequestException;
import com.findme.exception.DuplicateRecordException;
import com.findme.exception.UnknownServerException;
import com.findme.service.UserDetailService;
import com.findme.service.UserService;
import com.findme.utils.MessageUtility;
import com.findme.utils.UserRole;
import com.google.gson.JsonObject;

@RestController
public class RegisterController {

	private final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailService userDetailService;

	@Autowired
	private MessageUtility messageUtility;

	@RequestMapping(value = "/api/register", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> register(@RequestBody Map<String, Object> payload)
			throws DuplicateRecordException, BadRequestException, UnknownServerException {

		logger.info("Attempting to register new user");

		JsonObject parsedPayload = messageUtility.mapToJsonObject(payload);

		messageUtility.validateJsonObject(parsedPayload, new String[] { "username", "password", "email" });

		String username = parsedPayload.get("username").getAsString();
		String password = parsedPayload.get("password").getAsString();
		String email = parsedPayload.get("email").getAsString();

		userDetailService.createUserDetail(userService.createUser(username, password, email), UserRole.DEFAULT);

		logger.info("Successfully registered new user: " + username);

		return new ResponseEntity<Object>(
				messageUtility.createResponseMessage("success", "created new user: " + username), HttpStatus.CREATED);

	}

}
