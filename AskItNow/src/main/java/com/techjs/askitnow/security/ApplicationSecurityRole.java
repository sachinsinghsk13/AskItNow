package com.techjs.askitnow.security;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;
import static com.techjs.askitnow.security.ApplicationSecurityPermission.*;


public enum ApplicationSecurityRole {
	ADMIN(Sets.newHashSet(CATEGORY_READ, CATEGORY_WRITE, QUESTION_ANSWER_COMMENT_LIKE_READ,QUESTION_ANSWER_COMMENT_LIKE_WRITE )),
	MEMBER(Sets.newHashSet(CATEGORY_READ, QUESTION_ANSWER_COMMENT_LIKE_READ,QUESTION_ANSWER_COMMENT_LIKE_WRITE));

	private Set<ApplicationSecurityPermission> permissions;

	private ApplicationSecurityRole(Set<ApplicationSecurityPermission> permissions) {
		this.permissions = permissions;
	}
	
	public Set<ApplicationSecurityPermission> getPermissions() {
				return permissions;
	}
	
	public Set<SimpleGrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		authorities.addAll(getPermissions().stream().map(permisson -> new SimpleGrantedAuthority(permisson.getPermission()))
			.collect(Collectors.toSet()));
		authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		return authorities;
	}
}
