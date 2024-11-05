package com.starshop.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starshop.entities.Category;
import com.starshop.entities.Product;
import com.starshop.repositories.CategoryRepository;
import com.starshop.repositories.ProductRepository;
import com.starshop.services.CategoryService;

import jakarta.transaction.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ProductRepository productRepo;

	@Override
	public List<Category> findAll() {
		return categoryRepo.findAll();
	}
	
	@Override
	public List<Category> findByName(String name) {
		return categoryRepo.findByNameContainingIgnoreCase(name);
	}

	@Override
	public Category add(Category category) {
		return categoryRepo.save(category);
	}

	@Override
	public Category findById(Long id) {
		return categoryRepo.findById(id).orElse(null);
	}

	@Override
	public Category update(Category category) {
		return categoryRepo.save(category);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Category cate = categoryRepo.findById(id).orElse(null);
		if (cate != null) {
	        for (Product product : cate.getProducts()) {
	            product.removeCategory(cate);
	            productRepo.save(product);
	        }
			categoryRepo.delete(cate);
		}
	}

	@Override
	public void publishById(Long id) {
		Category cate = categoryRepo.findById(id).orElse(null);
		if (cate != null) {
			cate.setPublished(true);
			categoryRepo.save(cate);
		}
	}
	
	public void archiveById(Long id) {
		Category cate = categoryRepo.findById(id).orElse(null);
		if (cate != null) {
			cate.setPublished(false);
			categoryRepo.save(cate);
		}
	}
}
