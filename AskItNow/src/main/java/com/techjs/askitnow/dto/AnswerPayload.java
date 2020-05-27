package com.techjs.askitnow.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerPayload {
	private Long questionId;
	
	@NotNull
	@Size(min = 1, max = 512, message = "answer length must be between 1 to 512 characters")
	private String body;
	
	@Size(min = 0, max = 3, message = "You can only attach maximum 3 images in your answer")
	private List<MultipartFile> images;
}
