package com.starshop.services;

import java.util.List;
import java.util.UUID;

import com.starshop.entities.Shipper;

public interface ShipperService {
	List<Shipper> findAllShippers();
	
	Shipper add(Shipper shipper);
	
	void changeStatus(UUID shipperId);
	
	boolean isExisted(String email);
}
