package com.findme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * This class represents a user for the FindMe application
 * 
 * @author Daniel Cole
 *
 */
@Entity
@Table(name = "users")
public class User {

	@Column
	private String email;

	@Id
	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private boolean enabled;

	@OneToOne
	@JoinColumn(name = "username", referencedColumnName = "username")
	private UserDetail userDetail;

	// need to have default constructor for Hibernate
	protected User() {

	}

	public User(String username, String password, String email, boolean enabled) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return String.format(username);
	}

}
