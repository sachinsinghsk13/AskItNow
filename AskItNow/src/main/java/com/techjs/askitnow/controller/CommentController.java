package com.techjs.askitnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techjs.askitnow.dto.AnswerDto;
import com.techjs.askitnow.dto.CommentDto;
import com.techjs.askitnow.service.AnswerService;
import com.techjs.askitnow.service.CommentService;
import com.techjs.askitnow.service.QuestionService;

@RestController
@RequestMapping("/api/v1/questions/{questionId}/answers/{answerId}/comments")
public class CommentController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private CommentService commentService;

	@PostMapping
	public ResponseEntity<CommentDto> createComment(@PathVariable("questionId") Long questionId,
			@PathVariable("answerId") Long answerId, @RequestBody CommentDto commentDto) {
		CommentDto dto = commentService.createComment(questionId, answerId, commentDto);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@GetMapping
	public ResponseEntity<Page<CommentDto>> getAllComments(@PathVariable("questionId") Long questionId,
			@PathVariable("answerId") Long answerId, @PageableDefault Pageable pageable) {
		Page<CommentDto> page = commentService.getAllComments(questionId, answerId, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}

	@GetMapping("/{commentId}")
	public ResponseEntity<CommentDto> getComment() {
		return null;
	}
	
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<?> deleteComment() {
		System.out.println("delete comment");
		return null;
	}
	
	
	@PutMapping("/{commentId}")
	public ResponseEntity<?> updateComment() {
		System.out.println("update comment");
		return null;
	}
}
