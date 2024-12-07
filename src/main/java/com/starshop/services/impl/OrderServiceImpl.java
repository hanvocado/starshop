package com.starshop.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.starshop.entities.*;
import com.starshop.models.MonthlyReport;
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

	public Order updateOrderStatus(Long orderId, OrderStatus newStatus) {
		Order order = orderRepo.findById(orderId).orElse(null);
		if (order != null) {
			TrackingOrder newTracking = new TrackingOrder(order, newStatus, LocalDateTime.now());
			order.addTrackingOrder(newTracking);
			orderRepo.save(order);
		}
		return order;
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
		order.setTotalAndProfit();

		TrackingOrder placedOrder = new TrackingOrder(order, OrderStatus.PENDING, LocalDateTime.now());
		order.addTrackingOrder(placedOrder);

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
		List<Order> orders = orderRepo.findAll().stream().filter(order -> order.getCurrentStatus() == status)
				.collect(Collectors.toList());

		return new PageImpl<>(orders, pageable, orders.size());
	}

	@Override
	public Order assignShipper(Long orderId, UUID shipperId) {
		Order order = orderRepo.findById(orderId).orElse(null);
		Shipper shipper = (Shipper) userRepo.findById(shipperId).orElse(null);
		if (order != null && shipper != null) {
			TrackingOrder readyForShip = new TrackingOrder(order, OrderStatus.READY_FOR_SHIP, LocalDateTime.now());
			order.addTrackingOrder(readyForShip);
			order.setShipper(shipper);
			return orderRepo.save(order);
		}
		return null;
	}

	@Override
	public Order findByOrderId(Long orderId) {
		return orderRepo.findById(orderId).orElse(null);
	}

}
