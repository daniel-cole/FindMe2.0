package com.findme.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserDetail {

	@Id
	private String username;

	@Column(nullable = false)
	private String role;

	@MapsId
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	private User user;

	protected UserDetail() {

	}

	public UserDetail(User user, String role) {
		this.user = user;
		this.username = user.getUsername();
		this.role = role;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
