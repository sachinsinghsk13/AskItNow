package com.techjs.askitnow.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.techjs.askitnow.model.Address;
import com.techjs.askitnow.model.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	@Column(name = "id")
	private Long id;
	
	@NotNull
	@Size
	private String name;
	
	@NotNull(message = "username can't be null")
	@Size(message = "username must be mininum 4 characters and maximum 16 character long")
	private String username;
	
	@Email
	@NotNull
	private String email;
	
	private Gender gender;
	
	private Address address;
	
	private LocalDate dateOfBirth;
	
	private String profession;
	
	private String bio;
	
	private Instant created;
}
