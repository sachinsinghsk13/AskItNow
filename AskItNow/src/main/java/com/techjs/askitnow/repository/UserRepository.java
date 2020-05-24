package com.techjs.askitnow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.techjs.askitnow.model.User;

public interface UserRepository extends CrudRepository <User ,Long> {

	Optional<User> findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.active = true AND u.username = ?1")
	Optional<User> findActiveUserByUsername(String username);

}
