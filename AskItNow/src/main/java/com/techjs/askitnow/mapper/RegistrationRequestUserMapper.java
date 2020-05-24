package com.techjs.askitnow.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.techjs.askitnow.dto.RegistrationRequest;
import com.techjs.askitnow.model.User;

@Mapper(componentModel = "spring")
public interface RegistrationRequestUserMapper {
	
	public abstract User mapToUser(RegistrationRequest registrationRequest);

}
