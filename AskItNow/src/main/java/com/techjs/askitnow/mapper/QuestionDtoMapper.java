package com.techjs.askitnow.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.techjs.askitnow.dto.QuestionDto;
import com.techjs.askitnow.model.Question;
import com.techjs.askitnow.service.AnswerService;

@Mapper(componentModel = "spring")
public abstract class QuestionDtoMapper {

	@Autowired
	private AnswerService answerService;
	
	@Mapping(target = "postedBy", source = "question.postedBy.username")
	@Mapping(target = "totalAnswers", expression = "java(getAnswerCount(question))")
	@Mapping(target = "duration", expression = "java(getDuration(question))")
	public abstract QuestionDto mapToDto(Question question);
	
	protected Long getAnswerCount(Question question) {
		System.out.println("before answer count");
		return answerService.getAnswerCountByQuestion(question);
	}
	
	protected String getDuration(Question question) {
		return TimeAgo.using(question.getPostedTime().toEpochMilli());
	}
	
}
