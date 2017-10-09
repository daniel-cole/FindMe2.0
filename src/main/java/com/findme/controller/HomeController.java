package com.findme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.findme.service.UserImageService;
import com.findme.utils.MessageUtility;

@RestController
public class HomeController {

	private final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	UserImageService userImageService;

	@Autowired
	MessageUtility messageUtility;

	@RequestMapping(value = "/api/home", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> home(Authentication authentication) {

		String username = authentication.getName();

		return new ResponseEntity<Object>(messageUtility.createResponseMessage("response", "hello " + username),
				HttpStatus.OK);

	}

}
