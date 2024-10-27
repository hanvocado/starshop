package com.starshop.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starshop.models.Category;
import com.starshop.repositories.CategoryRepository;
import com.starshop.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository repo;

	@Override
	public List<Category> findAll() {
		return repo.findAll();
	}
	
	@Override
	public List<Category> findByName(String name) {
		return repo.findByNameContainingIgnoreCase(name);
	}

	@Override
	public Category add(Category category) {
		return repo.save(category);
	}

	@Override
	public Category findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Category update(Category category) {
		return repo.save(category);
	}

	@Override
	public void deleteById(Long id) {
		Category cate = repo.findById(id).orElse(null);
		if (cate != null) {
			repo.delete(cate);
		}
	}

	@Override
	public void publishById(Long id) {
		Category cate = repo.findById(id).orElse(null);
		if (cate != null) {
			cate.setPublished(true);
			repo.save(cate);
		}
	}
	
	public void archiveById(Long id) {
		Category cate = repo.findById(id).orElse(null);
		if (cate != null) {
			cate.setPublished(false);
			repo.save(cate);
		}
	}
}
