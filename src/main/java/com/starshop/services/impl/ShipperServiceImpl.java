package com.starshop.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starshop.entities.Shipper;
import com.starshop.repositories.ShipperRepository;
import com.starshop.services.ShipperService;

@Service
public class ShipperServiceImpl implements ShipperService {

	@Autowired
	private ShipperRepository shipperRepository;

	@Override
	public List<Shipper> findAllShippers() {
		return shipperRepository.findAll();
	}

}
