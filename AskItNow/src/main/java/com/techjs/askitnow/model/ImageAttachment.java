package com.techjs.askitnow.model;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageAttachment {
	private String filename;
	private String contentType;
	private String extension;
	private long size;
}