package com.techjs.askitnow.exception;

public class ResponseErrorDto {
	private String title;
	private Integer code;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
}
