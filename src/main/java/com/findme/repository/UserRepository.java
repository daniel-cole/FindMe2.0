package com.findme.repository;

import org.springframework.data.repository.CrudRepository;

import com.findme.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);

	User findByEmail(String email);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

}
