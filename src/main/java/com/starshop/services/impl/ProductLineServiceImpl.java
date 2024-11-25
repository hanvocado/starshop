package com.starshop.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starshop.entities.ProductLine;
import com.starshop.repositories.ProductLineRepository;
import com.starshop.services.ProductLineService;

@Service
public class ProductLineServiceImpl implements ProductLineService{
	@Autowired 
	private ProductLineRepository productLineRepository;

	@Override
	public Optional<ProductLine> findById(Long id) {
		return productLineRepository.findById(id);
	}
	
	
}
