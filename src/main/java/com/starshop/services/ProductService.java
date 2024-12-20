package com.starshop.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.starshop.entities.Product;

public interface ProductService {
	List<Product> getAll();
	List<Product> getPublishedOnesByName(String name);
	Product save(Product product);
	Product getById(Long id);
	void deleteById(Long id);
	Page<Product> getProductsPagination(Integer pageNo, Integer pageSize, String search);

	Page<Product> getPublishedProductsPagination(Integer pageNo, Integer pageSize, String search);
	
	Page<Product> getUnpublishedProductsPagination(Integer pageNo, Integer pageSize, String search);
	void save(Product product, List<Long> categoryIds);
	Page<Product> searchPublishedProductsByCategoryAndName(String name, String categoryName, int pageNo, int pageSize);
	Page<Product> getPublishedProductsByCategory(String categoryName, int pageNo, int pageSize);
}
