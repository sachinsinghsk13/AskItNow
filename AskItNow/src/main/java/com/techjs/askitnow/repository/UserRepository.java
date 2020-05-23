package com.techjs.askitnow.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.techjs.askitnow.model.User;

public interface UserRepository extends CrudRepository <User ,Long> {

	Optional<User> findByUsername(String username);

}
