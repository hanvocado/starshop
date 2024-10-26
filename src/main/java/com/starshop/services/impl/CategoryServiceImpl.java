package com.starshop.services.impl;

import java.util.List;
import java.util.Optional;

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
	public Category add(Category category) {
		Category cate = new Category(category.getName());
		return repo.save(cate);
	}

	@Override
	public Category findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Category update(Category category) {
		Category cate = new Category();
		cate.setName(category.getName());
		cate.setActivated(category.isActivated());
		cate.setDeleted(category.isDeleted());
		return repo.save(cate);
	}

	@Override
	public void deleteById(Long id) {
		Category cate = repo.findById(id).orElse(null);
		if (cate != null) {
			cate.setDeleted(true);
			cate.setActivated(false);
			repo.save(cate);
		}
	}

	@Override
	public void enableById(Long id) {
		Category cate = repo.findById(id).orElse(null);
		if (cate != null) {
			cate.setDeleted(false);
			cate.setActivated(true);
			repo.save(cate);
		}
	}

}
