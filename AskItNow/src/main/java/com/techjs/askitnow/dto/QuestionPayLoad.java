package com.techjs.askitnow.dto;

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
	private String title;
	private String body;
	private Long categoryId;
	private MultipartFile[] images;
}
