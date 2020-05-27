package com.techjs.askitnow.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageAttachment {
	private String filename;
	
	@Column(name = "content_type")
	private String contentType;
	private String extension;
	private Long size;
}
