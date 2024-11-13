package com.starshop.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.starshop.entities.Order;
import com.starshop.models.OrderStatus;
import com.starshop.models.ShipperRecord;
import com.starshop.repositories.OrderRepository;
import com.starshop.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	public int updateOrderStatus(Long orderId, OrderStatus newStatus) {
		return orderRepo.updateOrderStatusById(orderId, newStatus);
	}

	@Override
	public List<Order> findAll() {
		return orderRepo.findAll();
	}

	@Override
	public List<Order> findByUserId(UUID userId) {
		return orderRepo.findByUserId(userId);
	}

	@Override
	public Order add(Order order) {
		return orderRepo.save(order);
	}

	@Override
	public List<Order> findByShipperId(UUID shipperId) {
		return orderRepo.findByShipperId(shipperId);
	}

	@Override
	public List<ShipperRecord> getShipperRecords() {
		return orderRepo.getShipperRecords();
	}

	@Override
	public Optional<ShipperRecord> findShipperRecordByShipperId(UUID shipperId) {
		return orderRepo.findShipperRecordByShipperId(shipperId);
	}

	@Override
	public Long count() {
		return orderRepo.count();
	}

	@Override
	public Page<Order> findByStatus(OrderStatus status, Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return orderRepo.findByStatus(status, pageable);
	}
	
}
