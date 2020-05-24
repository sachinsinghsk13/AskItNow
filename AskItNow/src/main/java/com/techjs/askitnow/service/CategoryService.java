package com.techjs.askitnow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techjs.askitnow.exception.ResourceNotFoundException;
import com.techjs.askitnow.model.Category;
import com.techjs.askitnow.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Iterable<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	public Category getCategory(Long categoryId) {
		System.out.println("cate " + categoryId);
		return categoryRepository.findById(categoryId)
					.orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
	}

	public void updateCategory(Category category) {
		categoryRepository.save(category);
	}

	public void deleteCategory(Long categoryId) {
		categoryRepository.deleteById(categoryId);
	}
	
}
