package com.starshop.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starshop.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByIsPublishedTrue();
	
	Page<Product> findByIsPublishedTrue(Pageable pageable);
	
	Page<Product> findByIsPublishedFalse(Pageable pageable);
		
	List<Product> findByNameContainingIgnoreCaseAndIsPublishedTrue(String name);
	
	Page<Product> findByNameContainingIgnoreCaseAndIsPublishedTrue(String name, Pageable pageable);
	
	Page<Product> findByNameContainingIgnoreCaseAndIsPublishedFalse(String name, Pageable pageable);

	Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

}