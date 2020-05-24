package com.techjs.askitnow.security;

public enum ApplicationSecurityPermission {
	CATEGORY_READ("category:read"),
	CATEGORY_WRITE("category:write"),
	QUESTION_ANSWER_COMMENT_LIKE_READ("question_answer_comment_like:read"),
	QUESTION_ANSWER_COMMENT_LIKE_WRITE("question_answer_comment_like:write");
	
	private final String permission;
	
	private ApplicationSecurityPermission(String str) {
		this.permission = str;
	}
	
	public String getPermission() {
		return permission;
	}
}
