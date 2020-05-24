package com.techjs.askitnow.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techjs.askitnow.exception.UserNotFoundException;
import com.techjs.askitnow.model.User;
import com.techjs.askitnow.repository.UserRepository;

@Service
public class ActiveUsersDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findActiveUserByUsername(username)
				.orElseThrow(() -> new UserNotFoundException(String.format("User with the" +
						"username %s doesn't exist", username)));
		
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getSecurityRole().getAuthorities());
		return userDetails;
	}

}
