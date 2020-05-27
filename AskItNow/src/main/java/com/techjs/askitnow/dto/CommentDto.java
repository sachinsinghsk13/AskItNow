package com.techjs.askitnow.dto;

import java.time.Instant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

	private Long id;
	
	@NotNull
	@Size(min = 1, max = 40, message = "Comment length must be between 1 to 40 characters")
	private String body;
	private UserEmbeddedDto postedBy;
	private String duration;
	private Instant postedTime;
	private Long answerId;
}
