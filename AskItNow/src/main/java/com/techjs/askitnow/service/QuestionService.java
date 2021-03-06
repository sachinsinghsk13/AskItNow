package com.techjs.askitnow.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NoPermissionException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.techjs.askitnow.dto.ImageResponse;
import com.techjs.askitnow.dto.QuestionDto;
import com.techjs.askitnow.dto.QuestionPayLoad;
import com.techjs.askitnow.exception.ResourceNotFoundException;
import com.techjs.askitnow.mapper.QuestionDtoMapper;
import com.techjs.askitnow.model.Category;
import com.techjs.askitnow.model.ImageAttachment;
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

		if (questionPayLoad.getImages() != null && questionPayLoad.getImages().size() > 0) {
			for (MultipartFile image : questionPayLoad.getImages()) {
				ImageAttachment ia = new ImageAttachment();
				ia.setExtension(FilenameUtils.getExtension(image.getOriginalFilename()));
				ia.setContentType(image.getContentType());
				ia.setSize(image.getSize());

				StringBuilder sb = new StringBuilder();
				sb.append(user.getUsername());
				sb.append(question.getId());
				sb.append(FilenameUtils.removeExtension(image.getOriginalFilename()));

				ia.setFilename(sb.toString());

				FileOutputStream fos = new FileOutputStream(Constants.QUESTION_IMAGE_ATTACHMENT_DIRECTORY + "/"
						+ ia.getFilename() + "." + ia.getExtension());

				fos.write(image.getBytes());
				fos.flush();
				fos.close();

				question.getImageAttachments().add(ia);
			}
			question = questionRepository.save(question);
		}

		return questionDtoMapper.mapToDto(question);
	}

	public QuestionDto getQuestionDto(Long questionId) {
		Question question = questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException());
		return questionDtoMapper.mapToDto(question);
	}

	public ImageResponse getQuestionImage(Long questionId, String filename) throws IOException {
		QuestionDto dto = getQuestionDto(questionId);
		ImageAttachment ia = dto.getImageAttachments().stream().filter(ias -> ias.getFilename().equals(filename))
				.findFirst().orElseThrow(() -> new ResourceNotFoundException("Image Not Found"));

		String filenameWithExtension = ia.getFilename() + "." + ia.getExtension();
		FileInputStream fis = new FileInputStream(
				Constants.QUESTION_IMAGE_ATTACHMENT_DIRECTORY + "/" + filenameWithExtension);
		ImageResponse ir = new ImageResponse();
		ir.setData(fis.readAllBytes());
		fis.close();
		ir.setContentType(ia.getContentType());
		ir.setFilename(filenameWithExtension);
		return ir;
	}

	public Page<QuestionDto> getQuestionDtoByUser(String username, Pageable pageable) {
		Page<Question> questions = questionRepository.findQuestionByUser(username, pageable);
		return questions.map(question -> questionDtoMapper.mapToDto(question));
	}

	public Question getQuestionById(Long questionId) {
		return questionRepository.findById(questionId)
				.orElseThrow(() -> new ResourceNotFoundException("Question with id " + questionId + " is not found."));
	}

	public Page<QuestionDto> getRecentQuestions(Pageable pageable) {
		Page<Question> questions = questionRepository.findAll(pageable);
		return questions.map(q -> questionDtoMapper.mapToDto(q));
		
	}

	public void deleteQuestion(Long questionId) throws NoPermissionException {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Question question = questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException());

		if (!question.getPostedBy().getUsername().equals(userDetails.getUsername()))
			throw new NoPermissionException();
		questionRepository.delete(question);
		
	
	}
}
