package com.findme.repository;

import org.springframework.data.repository.CrudRepository;

import com.findme.model.UserDetail;

public interface UserDetailRepository extends CrudRepository<UserDetail, Long> {

	UserDetail findByUsername(String username);

}
