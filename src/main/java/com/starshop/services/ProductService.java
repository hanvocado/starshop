package com.starshop.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.starshop.models.Product;

public interface ProductService {
	List<Product> getAll();
	List<Product> getPublishedOnesByName(String name);
	Product add(Product product);
	Product getById(Long id);
	Product update(Product product);
	void deleteById(Long id);
	Page<Product> getAllProductsPagination(Integer pageNo, Integer pageSize);
	Page<Product> searchProductPagination(Integer pageNo, Integer pageSize, String search);
}
