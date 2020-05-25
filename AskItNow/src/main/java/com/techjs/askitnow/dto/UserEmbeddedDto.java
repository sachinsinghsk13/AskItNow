package com.techjs.askitnow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEmbeddedDto {
	private Long id;
	private String username;
	private String name;
}
