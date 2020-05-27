package com.techjs.askitnow.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techjs.askitnow.dto.ImageResponse;
import com.techjs.askitnow.dto.ProfileUpdatePayload;
import com.techjs.askitnow.dto.UserDto;
import com.techjs.askitnow.exception.PermissonDeniedException;
import com.techjs.askitnow.exception.ResourceNotFoundException;
import com.techjs.askitnow.exception.UserNotFoundException;
import com.techjs.askitnow.mapper.UserDtoMapper;
import com.techjs.askitnow.model.ImageAttachment;
import com.techjs.askitnow.model.User;
import com.techjs.askitnow.repository.UserRepository;
import com.techjs.askitnow.util.Constants;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDtoMapper userDtoMapper;

	public UserDto getUserDtoByUsername(String username) {
		User user = userRepository.findActiveUserByUsername(username).orElseThrow(() -> new UserNotFoundException());
		return userDtoMapper.mapToDto(user);
	}

	public User getActiveUserByUsername(String username) {
		return userRepository.findActiveUserByUsername(username).orElseThrow(() -> new UserNotFoundException());
	}

	@Transactional
	public UserDto updateProfile(ProfileUpdatePayload payload, String username)
			throws PermissonDeniedException, IOException {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!userDetails.getUsername().equals(username))
			throw new PermissonDeniedException("You don't have permisson to change this profile");
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User " + username + " is not found"));

		user.setName(payload.getName());
		user.setAddress(payload.getAddress());
		user.setProfession(payload.getProfession());
		user.setBio(payload.getBio());
		user.setGender(payload.getGender());
		user.setDateOfBirth(payload.getDateOfBirth());

		if (payload.getProfilePicture() != null) {
			ImageAttachment ia = new ImageAttachment();
			ia.setContentType(payload.getProfilePicture().getContentType());
			ia.setSize(payload.getProfilePicture().getSize());
			ia.setExtension(FilenameUtils.getExtension(payload.getProfilePicture().getOriginalFilename()));
			StringBuilder sb = new StringBuilder();
			sb.append(user.getUsername());
			sb.append(FilenameUtils.removeExtension(payload.getProfilePicture().getOriginalFilename()));
			ia.setFilename(sb.toString());
			FileOutputStream fos = new FileOutputStream(
					Constants.PROFILE_PICTURE_DIRECTORY + "/" + ia.getFilename() + "." + ia.getExtension());

			fos.write(payload.getProfilePicture().getBytes());
			fos.flush();
			fos.close();

			user.setProfilePicture(ia);
		}
		userRepository.save(user);
		return userDtoMapper.mapToDto(user);
	}

	public ImageResponse getProfilePicture(String username) throws IOException {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());
		if (user.getProfilePicture() == null)
			throw new ResourceNotFoundException();
		ImageAttachment ia = user.getProfilePicture();
		ImageResponse ir = new ImageResponse();
		ir.setContentType(ia.getContentType());
		ir.setFilename(ia.getFilename() + "." + ia.getExtension());
		
		FileInputStream fis = new FileInputStream(Constants.PROFILE_PICTURE_DIRECTORY + "/" + ir.getFilename());
		ir.setData(fis.readAllBytes());
		fis.close();
		return ir;
	}

}
