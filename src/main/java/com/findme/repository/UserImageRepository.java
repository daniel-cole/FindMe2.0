package com.findme.repository;

import org.springframework.data.repository.CrudRepository;

import com.findme.model.UserImage;

public interface UserImageRepository extends CrudRepository<UserImage, Long> {

	UserImage findByUsername(String username);

}
