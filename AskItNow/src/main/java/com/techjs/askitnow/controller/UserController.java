package com.techjs.askitnow.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techjs.askitnow.dto.ImageResponse;
import com.techjs.askitnow.dto.ProfileUpdatePayload;
import com.techjs.askitnow.dto.UserDto;
import com.techjs.askitnow.exception.PermissonDeniedException;
import com.techjs.askitnow.service.UserService;

@RestController
@RequestMapping("/api/v1/profiles")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{username}")
	public ResponseEntity<UserDto> getUserProfile(@PathVariable("username") String username) {
		return ResponseEntity
					.status(HttpStatus.OK)
					.body(userService.getUserDtoByUsername(username));
	}
	
	@PutMapping("/{username}")
	public ResponseEntity<UserDto> update(@PathVariable("username") String username, @ModelAttribute ProfileUpdatePayload payload) throws PermissonDeniedException, IOException {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(userService.updateProfile(payload, username));
	}
	
	@GetMapping("/{username}/profilePicture")
	public ResponseEntity<?> getProfilePicture(@PathVariable("username") String username) throws IOException {
		ImageResponse ir = userService.getProfilePicture(username);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaTypes(ir.getContentType()).get(0));
		headers.setContentDisposition(ContentDisposition.builder("attachment").filename(ir.getFilename()).build());
		return ResponseEntity
				.status(HttpStatus.OK)
				.headers(headers)
				.body(ir.getData());
	}
	
}
