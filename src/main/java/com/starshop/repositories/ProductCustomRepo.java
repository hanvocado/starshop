package com.starshop.repositories;

import java.util.List;

import com.starshop.models.Product;

public interface ProductCustomRepo {
	List<Product> findByName(String name);	
	List<Product> findAllExceptDeleted();
}
