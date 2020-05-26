package com.techjs.askitnow.dto;

import java.time.Instant;
import java.util.List;

import com.techjs.askitnow.model.ImageAttachment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
	
	private Long id;
	private String body;
	private String duration;
	private Instant postedTime;
	private UserEmbeddedDto postedBy;
	private List<ImageAttachment> imageAttachments;
	private Long questionId;
	private Long totalUpvotes;
	private Long totalDownvotes;
	private Long totalComments;
	
}
