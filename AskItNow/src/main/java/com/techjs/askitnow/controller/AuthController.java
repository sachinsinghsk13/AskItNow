package com.techjs.askitnow.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techjs.askitnow.dto.RegistrationRequest;
import com.techjs.askitnow.model.User;
import com.techjs.askitnow.repository.UserRepository;
import com.techjs.askitnow.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody @Valid RegistrationRequest registrationRequest) {
		authService.signup(registrationRequest);
		return ResponseEntity.status(HttpStatus.OK).body("User Registration Successfull");
	}
	
	@GetMapping("/accountVerification/{token}")
	public ResponseEntity<?> verifyAccount(@PathVariable("token") String token) {
		authService.verifyToken(token);
		return ResponseEntity.status(HttpStatus.OK).body("Verification Successful");
	}
	
	@GetMapping("/test")
	public void test() {

	}
}
