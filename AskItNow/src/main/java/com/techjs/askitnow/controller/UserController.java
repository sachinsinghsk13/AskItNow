package com.techjs.askitnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techjs.askitnow.dto.UserDto;
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
	
}
