package com.starshop.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.starshop.entities.Shipper;
import com.starshop.models.MonthlyShipperRecord;
import com.starshop.models.ShipperRecord;

public interface ShipperService {
	List<Shipper> findAllActiveShippers();
	
	Shipper add(Shipper shipper);
	
	void changeStatus(UUID shipperId);
	
	boolean isExisted(String email);

	ShipperRecord getRecordByShipperUsername(String username);

	List<MonthlyShipperRecord> getMonthlyRecordByShipperUsername(String username, int year);

	List<Shipper> findAllShippers();

	Optional<Shipper> findByUserName(String username);
}
