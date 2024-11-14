package com.starshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starshop.entities.ProductLine;

public interface ProductLineRepository extends JpaRepository<ProductLine, Long> {

}
