package com.techjs.askitnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techjs.askitnow.service.VoteService;

@RestController
@RequestMapping("/api/v1/questions/{questionId}/answers/{answerId}/votes")
public class VoteController {

	@Autowired
	private VoteService voteService;

	@PostMapping
	public ResponseEntity<?> createVote( ) {
		return null;
	}
	
}
