package com.techjs.askitnow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techjs.askitnow.model.Answer;
import com.techjs.askitnow.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;

	public Long getCommentCount(Answer answer) {
		return commentRepository.countByAnswer(answer);
	}
}
