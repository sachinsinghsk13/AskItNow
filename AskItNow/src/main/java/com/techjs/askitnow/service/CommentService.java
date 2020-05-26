package com.techjs.askitnow.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techjs.askitnow.dto.CommentDto;
import com.techjs.askitnow.mapper.CommentDtoMapper;
import com.techjs.askitnow.model.Answer;
import com.techjs.askitnow.model.Comment;
import com.techjs.askitnow.model.Question;
import com.techjs.askitnow.model.User;
import com.techjs.askitnow.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CommentDtoMapper commentDtoMapper;

	public Long getCommentCount(Answer answer) {
		return commentRepository.countByAnswer(answer);
	}

	@Transactional
	public CommentDto createComment(Long questionId, Long answerId, CommentDto commentDto) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getActiveUserByUsername(userDetails.getUsername());
		Question question = questionService.getQuestionById(questionId);
		Answer answer = answerService.getAnswerByQuestionAndId(question, answerId);
		Comment comment = new Comment();
		comment.setBody(commentDto.getBody());
		comment.setPostedTime(Instant.now());
		comment.setAnswer(answer);
		comment.setPostedBy(user);
		comment = commentRepository.save(comment); 
		return commentDtoMapper.mapToDto(comment);
	}

	public Page<CommentDto> getAllComments(Long questionId, Long answerId, Pageable pageable) {
		Question question = questionService.getQuestionById(questionId);
		Answer answer = answerService.getAnswerByQuestionAndId(question, answerId);
		Page<Comment> comments = commentRepository.findAllByAnswer(answer,pageable);
		return comments.map(comment -> commentDtoMapper.mapToDto(comment));
	}
}
