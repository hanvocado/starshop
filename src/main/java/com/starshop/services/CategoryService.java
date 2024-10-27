package com.starshop.services;

import java.util.List;

import com.starshop.models.Category;

public interface CategoryService {
	List<Category> findAll();
	List<Category> findByName(String name);
	Category add(Category category);
	Category findById(Long id);
	Category update(Category category);
	void deleteById(Long id);
	void publishById(Long id);
	void archiveById(Long id);
}
