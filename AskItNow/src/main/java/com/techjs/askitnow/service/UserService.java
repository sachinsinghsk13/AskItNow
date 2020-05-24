package com.techjs.askitnow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techjs.askitnow.dto.UserDto;
import com.techjs.askitnow.exception.UserNotFoundException;
import com.techjs.askitnow.mapper.UserDtoMapper;
import com.techjs.askitnow.model.User;
import com.techjs.askitnow.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDtoMapper userDtoMapper;
	
	public UserDto getUserDtoByUsername(String username) {
		User user = userRepository
					.findActiveUserByUsername(username)
					.orElseThrow(() -> new UserNotFoundException());
		return userDtoMapper.mapToDto(user);
	}
	
	public User getActiveUserByUsername(String username) {
		return userRepository.findActiveUserByUsername(username)
				.orElseThrow(() -> new UserNotFoundException());
	}
	
}
