package com.findme.utils;

public enum UserRole {
	DEFAULT("role_user"), ADMIN("role_admin");
	private final String role;

	UserRole(final String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return role;
	}

}
