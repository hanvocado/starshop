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
	public Page<Product> getAllProductsPagination(Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return repo.findAll(pageable);
	}

	@Override
	public Page<Product> searchProductPagination(Integer pageNo, Integer pageSize, String search) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return repo.findByNameContainingIgnoreCaseAndIsPublishedTrue(search, pageable);
	}
}
