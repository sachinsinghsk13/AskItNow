package com.techjs.askitnow.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.techjs.askitnow.dto.AnswerDto;
import com.techjs.askitnow.dto.UserEmbeddedDto;
import com.techjs.askitnow.model.Answer;
import com.techjs.askitnow.model.User;
import com.techjs.askitnow.service.CommentService;
import com.techjs.askitnow.service.VoteService;

@Mapper(componentModel = "spring")
public abstract class AnswerDtoMapper {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private VoteService voteService;
	
	@Autowired
	private UserEmbeddedDtoMapper userEmbeddedDtoMapper;
	
	@Mappings({
		@Mapping(target = "duration", expression = "java(getDuration(answer))"),
		@Mapping(target = "postedBy", expression = "java(mapToDto(answer.getPostedBy()))"),
		@Mapping(target = "questionId", source = "answer.question.id"),
		@Mapping(target = "totalUpvotes", expression = "java(getUpvoteCount(answer))"),
		@Mapping(target = "totalDownvotes", expression = "java(getDownvoteCount(answer))"),
		@Mapping(target = "totalComments", expression = "java(getCommentCount(answer))")
	})
	public abstract AnswerDto mapToDto(Answer answer);

	
	protected Long getCommentCount(Answer answer) {
		return commentService.getCommentCount(answer);
	}
	
	protected Long getUpvoteCount(Answer answer) {
		return voteService.getUpvoteCount(answer);
	}
	
	protected Long getDownvoteCount(Answer answer) {
		return voteService.getDownvoteCount(answer);
	}
	
	protected UserEmbeddedDto mapToDto(User user) {
		return userEmbeddedDtoMapper.mapToDto(user);
	}
	
	protected String getDuration(Answer answer) {
		return TimeAgo.using(answer.getPostedTime().toEpochMilli());
	}

}
