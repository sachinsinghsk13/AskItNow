package com.techjs.askitnow.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techjs.askitnow.dto.ImageResponse;
import com.techjs.askitnow.dto.QuestionDto;
import com.techjs.askitnow.dto.QuestionPayLoad;
import com.techjs.askitnow.model.Question;
import com.techjs.askitnow.repository.AnswerRepository;
import com.techjs.askitnow.service.AnswerService;
import com.techjs.askitnow.service.QuestionService;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerRepository;
	
	@GetMapping
	public ResponseEntity<List<QuestionDto>> getAllQuestions(@AuthenticationPrincipal UserDetails user) {
		System.out.println(user.getUsername());
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/by-user/{username}")
	public ResponseEntity<List<QuestionDto>> getQuestionByUser(@PathVariable("username") String username) {
		List<QuestionDto> questionDtos = questionService.getQuestionDtoByUser(username);
		return ResponseEntity.status(HttpStatus.OK).body(questionDtos);
		
	}
	
	@GetMapping("/{questionId}")
	public ResponseEntity<QuestionDto> getQuestion(@PathVariable("questionId") Long questionId) {
		return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionDto(questionId));
	}
	
	@GetMapping("/{questionId}/images/{filename}")
	public ResponseEntity<byte[]> getQuestionImage(@PathVariable("questionId") Long questionId,@PathVariable String filename) throws IOException {
		ImageResponse ir = questionService.getQuestionImage(questionId, filename);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", ir.getContentType());
		headers.set("Content-Disposition", "attachment; filename=" + filename);
		return new ResponseEntity<byte[]>(ir.getData(), headers,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<QuestionDto> postQuestion(@ModelAttribute QuestionPayLoad questionPayLoad, @AuthenticationPrincipal UserDetails user) throws IOException {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(questionService.postQuestion(questionPayLoad));
	}
}
