package com.techjs.askitnow.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import com.techjs.askitnow.model.Category;
import com.techjs.askitnow.model.QuestionImageAttachment;

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
	private String duration;
	private Category category;
	private String postedBy;
	private Long totalAnswers;
	private Collection<QuestionImageAttachment> imageAttachments;
}
