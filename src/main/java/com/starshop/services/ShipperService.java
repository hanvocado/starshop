package com.starshop.services;

import java.util.List;
import java.util.UUID;

import com.starshop.entities.Shipper;

public interface ShipperService {
	List<Shipper> findAllShippers();
	
	Shipper save(Shipper shipper);
	
	void changeStatus(UUID shipperId);
}
