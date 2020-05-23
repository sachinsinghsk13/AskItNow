package com.techjs.askitnow.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.techjs.askitnow.dto.RegistrationRequest;
import com.techjs.askitnow.model.User;

@Mapper(componentModel = "spring")
public abstract class RegistrationRequestUserMapper {
	
	@Autowired
	protected PasswordEncoder passwordEncoder;
	
	@Mapping(target = "password", expression = "java(encode(registrationRequest.getPassword()))")
	public abstract User mapToUser(RegistrationRequest registrationRequest);

	protected String encode(String s) {
		return passwordEncoder.encode(s);
	}
}
