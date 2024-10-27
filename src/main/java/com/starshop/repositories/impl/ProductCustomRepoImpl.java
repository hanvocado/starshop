package com.starshop.repositories.impl;

import java.util.List;

import com.starshop.models.Category;
import com.starshop.models.Product;
import com.starshop.repositories.ProductCustomRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class ProductCustomRepoImpl implements ProductCustomRepo {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Product> findByName(String name) {
		String jpql = "SELECT c FROM Product c WHERE c.name like :name and c.isDeleted = false";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
	}

	@Override
	public List<Product> findAllExceptDeleted() {
		String jpql = "SELECT c FROM Product c WHERE c.isDeleted = false";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        return query.getResultList();
	}

}
