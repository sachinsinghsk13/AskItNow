package com.techjs.askitnow.model;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "full_name", nullable = false)
	@NotNull
	@Size
	private String name;
	
	
	@NotNull(message = "username can't be null")
	@Size(min = 4, max = 16, message = "username must be mininum 4 characters and maximum 16 character long")
	@Column(name = "username")
	private String username;
	
	@NotNull(message = "password can't be null")
	@Size
	private String password;
	
	@Email
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private Address address;
	
	private Date dateOfBirth;
	
	private String profession;
	
	private String bio;
	
	private boolean active;
	
	private Instant created;
}
