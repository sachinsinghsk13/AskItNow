package com.techjs.askitnow.model;

import java.time.Instant;
import java.time.LocalDate;
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

import org.springframework.format.annotation.DateTimeFormat;

import com.techjs.askitnow.security.ApplicationSecurityRole;

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
	@NotNull(message = "Please must provide your name.")
	@Size(min = 3, max = 20, message = "name length must be between 3 to 20 characters")
	private String name;
	
	@NotNull(message = "username can't be null")
	@Size(min = 4, max = 20, message = "username length must be between 3 to 20 characters")
	@Column(name = "username")
	private String username;
	
	@NotNull(message = "password can't be null")
	private String password;
	
	@Email
	@NotNull(message = "Please Provide your valid email address.")
	private String email;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Gender gender;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "security_role")
	private ApplicationSecurityRole securityRole;
	
	private Address address;
	
	private ImageAttachment profilePicture;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;
	
	@Size(min = 0, max = 30, message = "profession length must be between 3 to 20 characters")
	private String profession;
	
	@Size(min = 0, max = 200, message = "bio length must be between 3 to 20 characters")
	private String bio;
	
	private boolean active;
	
	private Instant created;
}
