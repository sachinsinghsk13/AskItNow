package com.techjs.askitnow.repository;

import org.springframework.data.repository.CrudRepository;

import com.techjs.askitnow.model.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {

}
