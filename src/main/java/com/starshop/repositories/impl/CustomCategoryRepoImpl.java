package com.starshop.repositories.impl;

import java.util.List;

import com.starshop.models.Category;
import com.starshop.repositories.CustomCategoryRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class CustomCategoryRepoImpl implements CustomCategoryRepo {

	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Category> findByName(String name) {
        String jpql = "SELECT c FROM Category c WHERE c.name like :name";
        TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }
}
