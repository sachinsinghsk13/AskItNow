package com.techjs.askitnow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techjs.askitnow.model.Question;
import com.techjs.askitnow.repository.AnswerRepository;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository answerRepository;
	
	public Long getAnswerCountByQuestion(Question question) {
		return answerRepository.countByQuestion(question);
	}

}
