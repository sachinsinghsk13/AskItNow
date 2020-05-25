package com.techjs.askitnow.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.techjs.askitnow.model.Question;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {
	
	@Query("SELECT q FROM Question q WHERE q.postedBy.username = ?1")
	Page<Question> findQuestionByUser(String username, Pageable pageable);
}
