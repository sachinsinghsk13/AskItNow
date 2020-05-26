package com.techjs.askitnow.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

	private Long id;
	private String body;
	private UserEmbeddedDto postedBy;
	private String duration;
	private Instant postedTime;
	private Long answerId;
}
