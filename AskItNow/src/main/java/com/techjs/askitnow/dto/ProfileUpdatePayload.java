package com.techjs.askitnow.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.techjs.askitnow.model.Address;
import com.techjs.askitnow.model.Gender;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProfileUpdatePayload {
	
	@NotNull(message = "Please must provide your name.")
	@Size(min = 3, max = 25, message = "Your name length must be between 3 to 20 characters")
	private String name;
	
	@NotNull
	private Gender gender;
	
	private Address address;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;
	
	@Size(min = 0, max = 30, message = "profession length must be between 3 to 20 characters")
	private String profession;
	
	@Size(min = 0, max = 200, message = "bio length must be between 3 to 20 characters")
	private String bio;
	
	@Size(min = 1, max = 1, message = "Please Select only one file")
	private MultipartFile profilePicture;
}
