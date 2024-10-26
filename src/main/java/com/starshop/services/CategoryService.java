package com.starshop.services;

import java.util.List;

import com.starshop.models.Category;

public interface CategoryService {
	List<Category> findAll();
	Category add(Category category);
	Category findById(Long id);
	Category update(Category category);
	void deleteById(Long id);
	void enableById(Long id);
}
