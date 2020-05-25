package com.techjs.askitnow.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.techjs.askitnow.model.Answer;
import com.techjs.askitnow.model.Question;

public interface AnswerRepository extends PagingAndSortingRepository<Answer, Long>{
	Long countByQuestion(Question question);
	Page<Answer> findAllByQuestion(Question question, Pageable pageable);
	
}
