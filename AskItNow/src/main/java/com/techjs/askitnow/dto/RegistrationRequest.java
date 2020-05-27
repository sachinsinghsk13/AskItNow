package com.techjs.askitnow.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.techjs.askitnow.model.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
	
	@NotNull(message = "username can't be null")
	@Size(min = 4, max = 20, message = "username length must be between 4 to 20 characters")
	private String username;
	
	@NotNull(message = "password can't be null")
	@Size(min = 4, max = 12, message = "password length must be between 4 to 12 characters")
	private String password;
	
	@Email
	@NotNull(message = "Please Provide your valid email address.")
	private String email;
	
	@Column(name = "full_name", nullable = false)
	@NotNull(message = "Please must provide your name.")
	private String name;
	
	@NotNull
	private Gender gender;
}
