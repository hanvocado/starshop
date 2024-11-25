package com.starshop.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.starshop.entities.Order;
import com.starshop.models.ShipperRecord;
import com.starshop.utils.OrderStatus;

public interface OrderService {
	Order updateOrderStatus(Long orderId, OrderStatus newStatus);
	
	List<Order> findAll();
		
	Order add(Order order);

	List<Order> findByUserId(UUID userId);
	
	List<Order> findByShipperId(UUID shipperId);
	
	/*
	 * List<ShipperRecord> getShipperRecords();
	 * 
	 * Optional<ShipperRecord> findShipperRecordByShipperId(UUID shipperId);
	 */
	
	Long count();
	
	Page<Order> findByStatus(OrderStatus status, Integer pageNo, Integer pageSize);
	
	Order assignShipper(Long orderId, UUID shipperId);
	
	Order findByOrderId(Long orderId);
}
