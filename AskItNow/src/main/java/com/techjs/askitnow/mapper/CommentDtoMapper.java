package com.techjs.askitnow.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.techjs.askitnow.dto.CommentDto;
import com.techjs.askitnow.dto.UserEmbeddedDto;
import com.techjs.askitnow.model.Comment;
import com.techjs.askitnow.model.User;

@Mapper(componentModel = "spring")
public abstract class CommentDtoMapper {
	
	@Autowired
	private UserEmbeddedDtoMapper userEmbeddedDtoMapper;

	@Mappings({
		@Mapping(target = "duration", expression = "java(getDuration(comment))"),
		@Mapping(target = "postedBy", expression = "java(mapToDto(comment.getPostedBy()))"),
		@Mapping(target = "answerId", source = "comment.answer.id")
	})
	public abstract CommentDto mapToDto(Comment comment);

	protected UserEmbeddedDto mapToDto(User user) {
		return userEmbeddedDtoMapper.mapToDto(user);
	}
	
	protected String getDuration(Comment comment) {
		return TimeAgo.using(comment.getPostedTime().toEpochMilli());
	}
}
