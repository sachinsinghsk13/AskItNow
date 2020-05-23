package com.techjs.askitnow.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techjs.askitnow.dto.RegistrationRequest;

@RestController
@RequestMapping("/api/v1/auth")
public class RegistrationController {

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody @Valid RegistrationRequest registrationRequest) {
		return ResponseEntity.status(HttpStatus.OK).body("User Registration Successfull");
	}
	
	@PostMapping("/accountVerification/{token}")
	public ResponseEntity<?> verifyAccount(@PathVariable("token") String token) {
		return ResponseEntity.status(HttpStatus.OK).body("Verification Successful");
	}
}
