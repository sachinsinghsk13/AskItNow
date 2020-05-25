package com.techjs.askitnow.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.techjs.askitnow.model.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

}
