package com.findme.service;

import java.util.List;

import com.findme.exception.DuplicateRecordException;
import com.findme.exception.ResourceNotFoundException;
import com.findme.model.User;

/**
 * Interface which provides methods for querying against a database on the
 * FindMe users table
 * 
 * @author Daniel Cole
 *
 */
public interface UserService {

	/**
	 * 
	 * @return returns a list of FindMeUsers
	 */
	List<User> getUsers();

	/**
	 * 
	 * @param username
	 *            the username of the FindMeUser to retrieve
	 * @return returns the specified FindMeUser
	 */
	User getUser(String username);

	/**
	 * 
	 * @param username
	 *            the users username
	 * @param password
	 *            the password of the user
	 * @param email
	 *            the email of the user
	 * @return returns true if the user was successfully created; otherwise false
	 * @throws DuplicateUserException
	 */
	User createUser(String username, String password, String email) throws DuplicateRecordException;

	void disableUser(String username) throws ResourceNotFoundException;

	void enableUser(String username) throws ResourceNotFoundException;
}
