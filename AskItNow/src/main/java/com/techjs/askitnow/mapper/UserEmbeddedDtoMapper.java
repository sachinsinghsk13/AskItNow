package com.techjs.askitnow.mapper;

import org.mapstruct.Mapper;

import com.techjs.askitnow.dto.UserEmbeddedDto;
import com.techjs.askitnow.model.User;

@Mapper(componentModel = "spring")
public interface UserEmbeddedDtoMapper {
	
	UserEmbeddedDto mapToDto(User user);
}
