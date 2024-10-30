package com.starshop.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.starshop.models.Product;

public interface ProductService {
	List<Product> getAll();
	List<Product> getPublishedOnesByName(String name);
	Product add(Product product);
	Product getById(Long id);
	Product update(Product product);
	void deleteById(Long id);
	Page<Product> getProductsPagination(Integer pageNo, Integer pageSize, String search);

	Page<Product> getPublishedProductsPagination(Integer pageNo, Integer pageSize, String search);
	
	Page<Product> getUnpublishedProductsPagination(Integer pageNo, Integer pageSize, String search);
}
