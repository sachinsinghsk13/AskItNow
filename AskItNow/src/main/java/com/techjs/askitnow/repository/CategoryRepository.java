package com.techjs.askitnow.repository;

import org.springframework.data.repository.CrudRepository;

import com.techjs.askitnow.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
