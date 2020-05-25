package com.techjs.askitnow.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techjs.askitnow.dto.AnswerDto;
import com.techjs.askitnow.dto.AnswerPayload;
import com.techjs.askitnow.service.AnswerService;

@RestController
@RequestMapping("/api/v1/questions/{questionId}/answers")
public class AnswerController {
	
	@Autowired
	private AnswerService answerService;
	
	
	@GetMapping
	public ResponseEntity<Page<AnswerDto>> getAllAnswers(@PathVariable("questionId") Long questionId,@PageableDefault Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(answerService.getAllAnswers(questionId, pageable));
	}
	
	@PostMapping
	public ResponseEntity<AnswerDto> postAnswer(@PathVariable("questionId") Long questionId, @RequestBody AnswerPayload answerPayload) throws IOException {
		answerPayload.setQuestionId(questionId);
		return ResponseEntity.status(HttpStatus.CREATED).body(answerService.postAnswer(answerPayload));
	}

}
