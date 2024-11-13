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
import com.starshop.models.OrderStatus;
import com.starshop.models.ShipperRecord;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Modifying
    @Transactional
    @Query("UPDATE Order o SET o.status = :status WHERE o.id = :orderId")
    int updateOrderStatusById(@Param("orderId") Long orderId, @Param("status") OrderStatus status);
	//return the number of row affected
	
	List<Order> findByUserId(UUID userId);
	
	List<Order> findByShipperId(UUID shipperId);
	
	@Query("SELECT new com.starshop.models.ShipperRecord(o.shipper, "
	         + "SUM(CASE WHEN o.status = 'DELIVERED' THEN 1 ELSE 0 END), "
	         + "SUM(CASE WHEN o.status = 'SHIPFAILED' THEN 1 ELSE 0 END), "
	         + "SUM(CASE WHEN o.status = 'SHIPPING' THEN 1 ELSE 0 END)) "
	         + "FROM Order o GROUP BY o.shipper")
	List<ShipperRecord> getShipperRecords();
	
	@Query("SELECT new com.starshop.models.ShipperRecord(o.shipper, "
		     + "SUM(CASE WHEN o.status = 'DELIVERED' THEN 1 ELSE 0 END), "
		     + "SUM(CASE WHEN o.status = 'SHIPFAILED' THEN 1 ELSE 0 END), "
		     + "SUM(CASE WHEN o.status = 'SHIPPING' THEN 1 ELSE 0 END)) "
		     + "FROM Order o WHERE o.shipper.id = :shipperId GROUP BY o.shipper")
	Optional<ShipperRecord> findShipperRecordByShipperId(@Param("shipperId") UUID shipperId);	
	
	Page<Order> findByStatus(OrderStatus status, Pageable pageable);
}
