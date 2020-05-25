package com.techjs.askitnow.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.techjs.askitnow.dto.AnswerDto;
import com.techjs.askitnow.dto.AnswerPayload;
import com.techjs.askitnow.mapper.AnswerDtoMapper;
import com.techjs.askitnow.model.Answer;
import com.techjs.askitnow.model.ImageAttachment;
import com.techjs.askitnow.model.Question;
import com.techjs.askitnow.model.User;
import com.techjs.askitnow.repository.AnswerRepository;
import com.techjs.askitnow.util.Constants;

@Service
public class AnswerService {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private AnswerDtoMapper answerMapper;
	
	public Long getAnswerCountByQuestion(Question question) {
		return answerRepository.countByQuestion(question);
	}

	@Transactional
	public AnswerDto postAnswer(AnswerPayload answerPayload) throws IOException {
		Question question = questionService.getQuestionById(answerPayload.getQuestionId());
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getActiveUserByUsername(userDetails.getUsername());
		
		Answer answer = new Answer();
		answer.setBody(answerPayload.getBody());
		answer.setQuestion(question);
		answer.setPostedBy(user);
		answer.setPostedTime(Instant.now());
		
		answer = answerRepository.save(answer);
	
		if (answerPayload.getImages() != null && answerPayload.getImages().size() > 0) {
			for (MultipartFile image : answerPayload.getImages()) {
				ImageAttachment ia = new ImageAttachment();
				ia.setExtension(FilenameUtils.getExtension(image.getOriginalFilename()));
				ia.setContentType(image.getContentType());
				ia.setSize(image.getSize());

				StringBuilder sb = new StringBuilder();
				sb.append(user.getUsername());
				sb.append(question.getId());
				sb.append(FilenameUtils.removeExtension(image.getOriginalFilename()));

				ia.setFilename(sb.toString());

				FileOutputStream fos = new FileOutputStream(Constants.ANSWER_IMAGE_ATTACHMENT_DIRECTORY + "/"
						+ ia.getFilename() + "." + ia.getExtension());

				fos.write(image.getBytes());
				fos.flush();
				fos.close();

				answer.getImageAttachments().add(ia);
			}
			answer = answerRepository.save(answer);
		}
	
		return answerMapper.mapToDto(answer);
	}

	public Page<AnswerDto> getAllAnswers(Long questionId, Pageable pageable) {
		Question question = questionService.getQuestionById(questionId);
		Page<Answer> answers = answerRepository.findAllByQuestion(question, pageable);
		return answers.map(a -> answerMapper.mapToDto(a));
	}

}
