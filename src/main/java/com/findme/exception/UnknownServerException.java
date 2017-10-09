package com.findme.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class UnknownServerException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnknownServerException(String message) {
		super(message);
	}
}
