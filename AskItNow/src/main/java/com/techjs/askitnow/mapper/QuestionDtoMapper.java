package com.techjs.askitnow.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.techjs.askitnow.dto.QuestionDto;
import com.techjs.askitnow.dto.UserEmbeddedDto;
import com.techjs.askitnow.model.Question;
import com.techjs.askitnow.model.User;
import com.techjs.askitnow.service.AnswerService;

@Mapper(componentModel = "spring")
public abstract class QuestionDtoMapper {

	@Autowired
	private AnswerService answerService;
	@Autowired
	private UserEmbeddedDtoMapper userEmbeddedDtoMapper;
	
	
	@Mapping(target = "postedBy", expression = "java(mapToDto(question.getPostedBy()))")
	@Mapping(target = "totalAnswers", expression = "java(getAnswerCount(question))")
	@Mapping(target = "duration", expression = "java(getDuration(question))")
	public abstract QuestionDto mapToDto(Question question);
	
	protected Long getAnswerCount(Question question) {
		System.out.println("before answer count");
		return answerService.getAnswerCountByQuestion(question);
	}
	
	protected UserEmbeddedDto mapToDto(User user) {
		return userEmbeddedDtoMapper.mapToDto(user);
	}
	
	protected String getDuration(Question question) {
		return TimeAgo.using(question.getPostedTime().toEpochMilli());
	}
	
}
