package com.techjs.askitnow.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.techjs.askitnow.model.Answer;
import com.techjs.askitnow.model.Comment;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
	Long countByAnswer(Answer answer);
}
