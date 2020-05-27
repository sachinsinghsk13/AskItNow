package com.techjs.askitnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techjs.askitnow.model.Category;
import com.techjs.askitnow.service.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<?> getAllCategories(Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(categoryService.getAllCategory(pageable));
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<?> getCategory(@PathVariable("categoryId") Long categoryId) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(categoryService.getCategory(categoryId));
	}
	
	@PostMapping
	public ResponseEntity<?> createCategory(@RequestBody Category category) {
		Category savedCategory = categoryService.save(category);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(savedCategory);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<?> updateCategory(@PathVariable("categoryId") Long categoryId ,@RequestBody Category category) {
		category.setId(categoryId);
		categoryService.updateCategory(category);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Long categoryId) {
		categoryService.deleteCategory(categoryId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
