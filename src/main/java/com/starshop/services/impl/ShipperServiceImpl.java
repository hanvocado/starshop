package com.starshop.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.starshop.entities.Shipper;
import com.starshop.models.AppException;
import com.starshop.models.MonthlyShipperRecord;
import com.starshop.models.ShipperRecord;
import com.starshop.repositories.OrderRepository;
import com.starshop.repositories.ShipperRepository;
import com.starshop.repositories.UserRepository;
import com.starshop.services.ShipperService;

@Service
public class ShipperServiceImpl implements ShipperService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ShipperRepository shipperRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Shipper> findAllShippers() {
		return shipperRepository.findByOrderByIsActiveDesc();
	}

	@Override
	public Shipper add(Shipper shipper) {
		if (userRepository.existsByEmail(shipper.getEmail())) {
			throw new AppException(400, "Email đã tồn tại.");
		}
		shipper.setActive(true);
		shipper.setCreatedAt(LocalDateTime.now());
		shipper.setUserName(shipper.getEmail());
		shipper.setPassword(passwordEncoder.encode(shipper.getPassword()));		
		return shipperRepository.save(shipper);
	}

	@Override
	public void changeStatus(UUID shipperId) {
		Shipper shipper = shipperRepository.findById(shipperId).orElseThrow();
		shipper.setActive(!shipper.isActive());
		shipperRepository.save(shipper);
	}
	
	@Override
	public boolean isExisted(String email) {
		if (userRepository.existsByEmail(email)) {
			return true;
		}
		return false;
	}
	
	@Override
	public ShipperRecord getRecordByShipperUsername(String username) {
		return orderRepository.findShipperRecordByShipperUsername(username).orElseThrow();
	}

	@Override
	public List<MonthlyShipperRecord> getMonthlyRecordByShipperUsername(String username, int year) {
		return orderRepository.getMonthlyRecordByUsername(username, year);
	}
}
