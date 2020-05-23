package com.techjs.askitnow.repository;

import org.springframework.data.repository.CrudRepository;

import com.techjs.askitnow.model.User;

public interface UserRepository extends CrudRepository <User ,Long> {

}
