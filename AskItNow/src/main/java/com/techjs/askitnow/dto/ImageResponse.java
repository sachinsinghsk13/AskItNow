package com.techjs.askitnow.dto;

import lombok.Data;

@Data
public class ImageResponse {
	private byte[] data;
	private String contentType;
	private String filename;
}
