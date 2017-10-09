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
@Table(name = "user_image")
public class UserImage {

	@Id
	private String username;

	@Column(nullable = false)
	private String imageUrl;

	@MapsId
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	private User user;

	protected UserImage() {

	}

	public UserImage(User user, String imageUrl) {
		this.user = user;
		this.username = user.getUsername();
		this.imageUrl = imageUrl;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
