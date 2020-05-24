package com.techjs.askitnow.repository;

import org.springframework.data.repository.CrudRepository;

import com.techjs.askitnow.model.Answer;
import com.techjs.askitnow.model.Question;

public interface AnswerRepository extends CrudRepository<Answer, Long>{
	Long countByQuestion(Question question);
	
}
