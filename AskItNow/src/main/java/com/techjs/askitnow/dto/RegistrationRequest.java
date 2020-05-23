package com.techjs.askitnow.dto;

import java.util.Date;

import com.techjs.askitnow.model.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
	private String username;
	private String password;
	private String email;
	private String fullname;
	private Date dateOfBirth;
	private Gender gender;
	
}
