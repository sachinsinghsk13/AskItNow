package com.techjs.askitnow.repository;

import org.springframework.data.repository.CrudRepository;

import com.techjs.askitnow.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
