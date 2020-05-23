package com.techjs.askitnow.repository;

import org.springframework.data.repository.CrudRepository;

import com.techjs.askitnow.model.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {

}
