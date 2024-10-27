package com.starshop.services;

import java.util.List;

import com.starshop.models.Product;

public interface ProductService {
	List<Product> findAll();
	List<Product> findByName(String name);
	Product add(Product category);
	Product findById(Long id);
	Product update(Product category);
	void deleteById(Long id);
}
