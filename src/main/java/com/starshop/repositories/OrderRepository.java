package com.starshop.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.starshop.entities.Order;
import com.starshop.models.ShipperRecord;
import com.starshop.utils.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByUserId(UUID userId);
	
	List<Order> findByShipperId(UUID shipperId);
	
	/*
	 * @Query("SELECT new com.starshop.models.ShipperRecord(o.shipper, " +
	 * "SUM(CASE WHEN o.currentStatus = 'DELIVERED' THEN 1 ELSE 0 END), " +
	 * "SUM(CASE WHEN o.currentStatus = 'SHIPFAILED' THEN 1 ELSE 0 END), " +
	 * "SUM(CASE WHEN o.currentStatus = 'SHIPPING' THEN 1 ELSE 0 END)) " +
	 * "FROM Order o GROUP BY o.shipper") List<ShipperRecord> getShipperRecords();
	 * 
	 * @Query("SELECT new com.starshop.models.ShipperRecord(o.shipper, " +
	 * "SUM(CASE WHEN o.currentStatus = 'DELIVERED' THEN 1 ELSE 0 END), " +
	 * "SUM(CASE WHEN o.currentStatus = 'SHIPFAILED' THEN 1 ELSE 0 END), " +
	 * "SUM(CASE WHEN o.currentStatus = 'SHIPPING' THEN 1 ELSE 0 END)) " +
	 * "FROM Order o WHERE o.shipper.id = :shipperId GROUP BY o.shipper")
	 * Optional<ShipperRecord> findShipperRecordByShipperId(@Param("shipperId") UUID
	 * shipperId);
	 */
	
}
