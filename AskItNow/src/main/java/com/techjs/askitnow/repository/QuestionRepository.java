package com.techjs.askitnow.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.techjs.askitnow.model.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {
	
	@Query("SELECT q FROM Question q WHERE q.postedBy.username = ?1")
	Iterable<Question> findQuestionByUser(String username);
}
