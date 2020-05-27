package com.techjs.askitnow.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionPayLoad {
	@NotNull
	@Size(min = 5, max = 80, message = "Your question title length must be between 5 to 80 characters long")
	private String title;
	
	@Size(min = 1, max = 256, message = "Your question body length must be between 1 to 256 characters long")
	private String body;
	
	private Long categoryId;
	
	@Size(min = 0, max = 3, message = "Your can only attach maximum 3 images to your question.")
	private List<MultipartFile> images;
}
