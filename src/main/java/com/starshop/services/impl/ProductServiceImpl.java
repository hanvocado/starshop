package com.starshop.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.starshop.entities.Category;
import com.starshop.entities.Product;
import com.starshop.repositories.CategoryRepository;
import com.starshop.repositories.ProductRepository;
import com.starshop.services.ProductService;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public List<Product> getAll() {
		return productRepo.findAll();
	}

	@Override
	public List<Product> getPublishedOnesByName(String name) {
		return productRepo.findByNameContainingIgnoreCaseAndIsPublishedTrue(name);
	}
	
	@Override
	@Transactional
    public void save(Product product, List<Long> categoryIds) {	
		if (product != null) {
			productRepo.save(product);
			product.getCategories().clear();
			if (categoryIds != null) {
				for (Long categoryId : categoryIds) {
					Category category = categoryRepo.findById(categoryId).orElse(null);
					if (category != null) {
						product.addCategory(category);	        
					}
				}        
				productRepo.save(product);	
				
			}
		}		
    }

	@Override
	public Product save(Product product) {
		return productRepo.save(product);
	}

	@Override
	public Product getById(Long id) {
		if (id == null)
			return null;
		return productRepo.findById(id).orElse(null);
	}
	
	@Override
	public void deleteById(Long id) {
		Product product = productRepo.findById(id).orElse(null);
		if (product != null) {
			for (Category category : product.getCategories()) {
	            category.getProducts().remove(product);
	            categoryRepo.save(category);
	        }
			
			productRepo.delete(product);
		}
	}

	@Override
	public Page<Product> getProductsPagination(Integer pageNo, Integer pageSize, String search) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		if (search != null && !search.isBlank()) {
			productRepo.findByNameContainingIgnoreCase(search, pageable);
		}
		return productRepo.findAll(pageable);
	}

	@Override
	public Page<Product> getPublishedProductsPagination(Integer pageNo, Integer pageSize, String search) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		if (search != null && !search.isBlank()) {
			productRepo.findByNameContainingIgnoreCaseAndIsPublishedTrue(search, pageable);
		}
		return productRepo.findByIsPublishedTrue(pageable);
	}

	@Override
	public Page<Product> getUnpublishedProductsPagination(Integer pageNo, Integer pageSize, String search) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		if (search != null && !search.isBlank()) {
			productRepo.findByNameContainingIgnoreCaseAndIsPublishedFalse(search, pageable);
		}
		return productRepo.findByIsPublishedFalse(pageable);
	}
}
