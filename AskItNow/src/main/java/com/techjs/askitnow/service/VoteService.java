package com.techjs.askitnow.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.techjs.askitnow.dto.VoteDto;
import com.techjs.askitnow.exception.NotApplicableException;
import com.techjs.askitnow.model.Answer;
import com.techjs.askitnow.model.Question;
import com.techjs.askitnow.model.User;
import com.techjs.askitnow.model.Vote;
import com.techjs.askitnow.repository.VoteRepository;

@Service
public class VoteService {

	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private UserService userService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerService answerService;
	
	public Long getUpvoteCount(Answer answer) {
		return voteRepository.countUpvotesByAnswer(answer);
	}

	public Long getDownvoteCount(Answer answer) {
		return voteRepository.countDownvotesByAnswer(answer);
	}

	public Vote doVote(Long questionId, Long answerId, VoteDto votedto) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getActiveUserByUsername(userDetails.getUsername());
		
		Question question = questionService.getQuestionById(questionId);
		Answer answer = answerService.getAnswerByQuestionAndId(question, answerId);
		Optional<Vote> vote = voteRepository.findByAnswerAndVotedBy(answer, user);
		Vote v;
		if (vote.isEmpty()) {
			v = new Vote();
			v.setAnswer(answer);
			v.setVotedBy(user);
			v.setVoteType(votedto.getVoteType());
			voteRepository.save(v);
		}
		else if (vote.get().getVoteType().name().equals(votedto.getVoteType().name())) {
			throw new NotApplicableException("Same Vote already exist");
		}
		else {
			v = vote.get();
			v.setVoteType(votedto.getVoteType());
			voteRepository.save(v);
		}
		return v;
	}

}
