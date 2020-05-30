package com.techjs.askitnow.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.naming.NoPermissionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	private AnswerService answerService;
	
	@GetMapping
	public ResponseEntity<Page<QuestionDto>> getAllQuestions(@PageableDefault(sort = "postedTime", direction = Direction.DESC) Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(questionService.getRecentQuestions(pageable));
	}
	
	@GetMapping("/by-user/{username}")
	public ResponseEntity<Page<QuestionDto>> getQuestionByUser(@PathVariable("username") String username,@PageableDefault(sort = {"postedTime"}, direction = Direction.DESC) Pageable pageable) {
		Page<QuestionDto> questionDtos = questionService.getQuestionDtoByUser(username, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(questionDtos);
		
	}
	
	@GetMapping("/{questionId}")
	public ResponseEntity<QuestionDto> getQuestion(@PathVariable("questionId") Long questionId) {
		return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionDto(questionId));
	}
	
	
	@PutMapping("/{questionId}")
	public ResponseEntity<QuestionDto> updateQuestion(@ModelAttribute QuestionPayLoad questionPayLoad) {
		System.out.println("update queswtoins");
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(null);
	}
	
	
	@DeleteMapping("/{questionId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable("questionId") Long questionId) throws NoPermissionException {
		questionService.deleteQuestion(questionId);
		return ResponseEntity
				.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/{questionId}/images/{filename}")
	public ResponseEntity<byte[]> getQuestionImage(@PathVariable("questionId") Long questionId,@PathVariable String filename) throws IOException {
		ImageResponse ir = questionService.getQuestionImage(questionId, filename);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaTypes(ir.getContentType()).get(0));
		headers.setContentDisposition(ContentDisposition.builder("attachment").filename(ir.getFilename()).build());
		return ResponseEntity
				.status(HttpStatus.OK)
				.headers(headers)
				.body(ir.getData());
	}
	
	@PostMapping
	public ResponseEntity<QuestionDto> postQuestion(@ModelAttribute QuestionPayLoad questionPayLoad) throws IOException {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(questionService.postQuestion(questionPayLoad));
	}
}
