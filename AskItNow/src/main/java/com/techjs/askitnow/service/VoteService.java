package com.techjs.askitnow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techjs.askitnow.model.Answer;
import com.techjs.askitnow.repository.VoteRepository;

@Service
public class VoteService {

	@Autowired
	private VoteRepository voteRepository;

	public Long getUpvoteCount(Answer answer) {
		return voteRepository.countUpvotesByAnswer(answer);
	}

	public Long getDownvoteCount(Answer answer) {
		return voteRepository.countDownvotesByAnswer(answer);
	}

}
