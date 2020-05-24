package com.techjs.askitnow.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techjs.askitnow.dto.RegistrationRequest;
import com.techjs.askitnow.exception.InvalidVerificationToken;
import com.techjs.askitnow.exception.UserNotFoundException;
import com.techjs.askitnow.mapper.RegistrationRequestUserMapper;
import com.techjs.askitnow.model.NotificationEmail;
import com.techjs.askitnow.model.User;
import com.techjs.askitnow.model.VerificationToken;
import com.techjs.askitnow.repository.UserRepository;
import com.techjs.askitnow.repository.VerificationTokenRepository;
import com.techjs.askitnow.security.ApplicationSecurityRole;

@Service
public class AuthService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RegistrationRequestUserMapper registrationRequestUserMapper;

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Autowired
	private MailService mailService;
	
	public void signup(RegistrationRequest registrationRequest) {
		
		User user = registrationRequestUserMapper.mapToUser(registrationRequest);
		user.setCreated(Instant.now());
		user.setActive(false);
		user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
		user.setSecurityRole(ApplicationSecurityRole.MEMBER);
		userRepository.save(user);
		String token = generateVerificationToken(user);
		NotificationEmail ne = new NotificationEmail();
		ne.setSubject("Please Activate Your Account");
		ne.setRecipient(registrationRequest.getEmail());
		StringBuilder sb = new StringBuilder();
		sb.append("Thank You for signing up for AskItNow. Activate Your account by ");
		sb.append("clicking on below link");
		sb.append("<a href='http://localhost:8080/api/v1/auth/accountVerification/"+token+"'>Verify Account</a>");
		ne.setBody(sb.toString());
		mailService.sendMail(ne);
		
	}
	
	private void fetchUserAndActivate(VerificationToken vt) {
		String username = vt.getUser().getUsername();
		User user = userRepository.findByUsername(username)
				.orElseThrow(()-> new UserNotFoundException("User for username" + username +" not found"));
		user.setActive(true);
		userRepository.save(user);
	}
	
	public void verifyToken(String token) {
		Optional<VerificationToken> verficationToken = verificationTokenRepository.findByToken(token);
		fetchUserAndActivate(verficationToken.orElseThrow(()-> new InvalidVerificationToken()));
	}
	
	private String generateVerificationToken(User user) {
		VerificationToken verificationToken = new VerificationToken();
		String token = UUID.randomUUID().toString();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);
		return token;
	}
	
}
