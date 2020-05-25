package com.techjs.askitnow.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerPayload {
	private Long questionId;
	private String body;
	private List<MultipartFile> images;
}
