package com.starshop.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.starshop.models.Product;
import com.starshop.repositories.ProductRepository;
import com.starshop.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository repo;
	
	@Override
	public List<Product> getAll() {
		return repo.findAll();
	}

	@Override
	public List<Product> getPublishedOnesByName(String name) {
		return repo.findByNameContainingIgnoreCaseAndIsPublishedTrue(name);
	}

	@Override
	public Product add(Product product) {
		return repo.save(product);
	}

	@Override
	public Product getById(Long id) {
		if (id == null)
			return null;
		return repo.findById(id).orElse(null);
	}

	@Override
	public Product update(Product product) {
		return repo.save(product);
	}

	@Override
	public void deleteById(Long id) {
		Product p = repo.findById(id).orElse(null);
		if (p != null) {
			repo.delete(p);
		}
	}

	@Override
	public Page<Product> getProductsPagination(Integer pageNo, Integer pageSize, String search) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		if (search != null && !search.isBlank()) {
			repo.findByNameContainingIgnoreCase(search, pageable);
		}
		return repo.findAll(pageable);
	}

	@Override
	public Page<Product> getPublishedProductsPagination(Integer pageNo, Integer pageSize, String search) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		if (search != null && !search.isBlank()) {
			repo.findByNameContainingIgnoreCaseAndIsPublishedTrue(search, pageable);
		}
		return repo.findByIsPublishedTrue(pageable);
	}

	@Override
	public Page<Product> getUnpublishedProductsPagination(Integer pageNo, Integer pageSize, String search) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		if (search != null && !search.isBlank()) {
			repo.findByNameContainingIgnoreCaseAndIsPublishedFalse(search, pageable);
		}
		return repo.findByIsPublishedFalse(pageable);
	}

}
