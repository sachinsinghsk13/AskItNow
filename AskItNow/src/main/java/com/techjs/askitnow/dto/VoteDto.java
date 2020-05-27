package com.techjs.askitnow.dto;

import com.techjs.askitnow.model.VoteType;

import lombok.Data;

@Data
public class VoteDto {
	private VoteType voteType;
	private Long answerId;
}
