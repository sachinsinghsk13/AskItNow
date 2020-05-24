package com.techjs.askitnow.mapper;

import org.mapstruct.Mapper;

import com.techjs.askitnow.dto.UserDto;
import com.techjs.askitnow.model.User;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
	
	UserDto mapToDto(User user);
}
