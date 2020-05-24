package com.techjs.askitnow.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetail {
	private String title;
	private int status;
	private long timestamp;
	private String detail;
	private String developerMessage;
	private Map<String, List<ValidationError>> validationErrors = new HashMap<>();
	
	public Map<String, List<ValidationError>> getValidationErrors() {
		return validationErrors;
	}
	public void setValidationErrors(Map<String, List<ValidationError>> validationErrors) {
		this.validationErrors = validationErrors;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getDeveloperMessage() {
		return developerMessage;
	}
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
	
}
