package com.starshop.services;

import java.util.List;
import java.util.Optional;

import com.starshop.entities.ProductLine;

public interface ProductLineService {

	Optional<ProductLine> findById(Long id);

	List<ProductLine> getProductLinesByIds(String selectedProductLineIds);

}
