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
import com.starshop.entities.User;
import com.starshop.models.ShipperRecord;
import com.starshop.repositories.OrderRepository;
import com.starshop.repositories.UserRepository;
import com.starshop.services.OrderService;
import com.starshop.utils.OrderStatus;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private UserRepository userRepo;
	
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

	@Override
	public Order assignShipper(Long orderId, UUID shipperId) {
		Order order = orderRepo.findById(orderId).orElse(null);
		User shipper = userRepo.findById(shipperId).orElse(null);
		if (order != null && shipper != null) {
			order.setStatus(OrderStatus.READYFORSHIP);
			order.setShipper(shipper);
			return orderRepo.save(order);
		}
		return null;
	}
	
}
