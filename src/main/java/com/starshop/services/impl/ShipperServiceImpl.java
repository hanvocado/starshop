package com.starshop.services.impl;

import java.util.List;
import java.util.UUID;

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
		return shipperRepository.findByOrderByIsActiveDesc();
	}

	@Override
	public Shipper save(Shipper shipper) {
		return shipperRepository.save(shipper);
	}

	@Override
	public void changeStatus(UUID shipperId) {
		Shipper shipper = shipperRepository.findById(shipperId).orElseThrow();
		shipper.setActive(!shipper.isActive());
		shipperRepository.save(shipper);
	}

}
