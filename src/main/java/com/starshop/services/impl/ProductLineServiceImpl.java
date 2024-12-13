package com.starshop.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starshop.entities.ProductLine;
import com.starshop.repositories.ProductLineRepository;
import com.starshop.services.ProductLineService;

@Service
public class ProductLineServiceImpl implements ProductLineService {
	@Autowired
	private ProductLineRepository productLineRepository;

	@Override
	public Optional<ProductLine> findById(Long id) {
		return productLineRepository.findById(id);
	}

	@Override
	public List<ProductLine> getProductLinesByIds(String selectedProductLineIds) {
	    List<Long> productLineIds = Arrays.stream(selectedProductLineIds.split(","))
	                                      .map(Long::parseLong)
	                                      .collect(Collectors.toList());
	    return productLineRepository.findAllById(productLineIds);
	}

}
