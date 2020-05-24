package com.techjs.askitnow.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.techjs.askitnow.dto.QuestionDto;
import com.techjs.askitnow.dto.QuestionPayLoad;
import com.techjs.askitnow.exception.ResourceNotFoundException;
import com.techjs.askitnow.mapper.QuestionDtoMapper;
import com.techjs.askitnow.model.Category;
import com.techjs.askitnow.model.QuestionImageAttachment;
import com.techjs.askitnow.model.Question;
import com.techjs.askitnow.model.User;
import com.techjs.askitnow.repository.QuestionRepository;
import com.techjs.askitnow.util.Constants;

@Service
public class QuestionService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private QuestionDtoMapper questionDtoMapper;
	
	@Transactional
	public QuestionDto postQuestion(QuestionPayLoad questionPayLoad) throws IOException {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getActiveUserByUsername(userDetails.getUsername());
		Question question = new Question();
		question.setTitle(questionPayLoad.getTitle());
		question.setBody(questionPayLoad.getBody());
		
		Category category = categoryService.getCategory(questionPayLoad.getCategoryId());
		question.setCategory(category);
		question.setPostedBy(user);
		question.setPostedTime(Instant.now());
		
		question = questionRepository.save(question);
		
		for (MultipartFile image : questionPayLoad.getImages()) {
			
			StringBuilder sb = new StringBuilder();
			sb.append(user.getUsername());
			sb.append(question.getId());
			sb.append(image.getOriginalFilename());
			
			QuestionImageAttachment imageAttachment = new QuestionImageAttachment();
			imageAttachment.setFilename(sb.toString());
			imageAttachment.setContentType(image.getContentType());
			imageAttachment.setSize(image.getSize());
			
			FileOutputStream fos = new FileOutputStream(Constants.QUESTION_IMAGE_ATTACHMENT_DIRECTORY+"/"+sb.toString());
			fos.write(image.getBytes());
			fos.flush();
			fos.close();
			
			
			question.getImageAttachments().add(imageAttachment);
		}
		question = questionRepository.save(question);
		return questionDtoMapper.mapToDto(question);
	}
	
	public QuestionDto getQuestionDto(Long questionId) {
		Question question = questionRepository.findById(questionId)
				.orElseThrow(() -> new ResourceNotFoundException());
		return questionDtoMapper.mapToDto(question);
	}

	public List<QuestionDto> getQuestionDtoByUser(String username) {
		return null;
	}
}
