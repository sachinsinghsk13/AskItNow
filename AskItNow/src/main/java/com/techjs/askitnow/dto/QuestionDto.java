package com.techjs.askitnow.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import com.techjs.askitnow.model.Category;
import com.techjs.askitnow.model.ImageAttachment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
	private Long id;
	private String title;
	private String body;
	private Instant postedTime;
	private String duration;
	private Category category;
	private UserEmbeddedDto postedBy;
	private Long totalAnswers;
	private Collection<ImageAttachment> imageAttachments;
}
