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

import com.starshop.entities.Customer;
import com.starshop.entities.Order;
import com.starshop.models.MonthlyReport;
import com.starshop.models.MonthlyShipperRecord;
import com.starshop.models.ShipperRecord;
import com.starshop.utils.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByUserId(UUID userId);

	List<Order> findByShipperId(UUID shipperId);

	@Query("SELECT new com.starshop.models.ShipperRecord(o.shipper, "
			+ "SUM(CASE WHEN o.currentStatus = com.starshop.utils.OrderStatus.DELIVERED THEN 1 ELSE 0 END), "
			+ "SUM(CASE WHEN o.currentStatus = com.starshop.utils.OrderStatus.SHIP_FAILED THEN 1 ELSE 0 END), "
			+ "SUM(CASE WHEN o.currentStatus = com.starshop.utils.OrderStatus.SHIPPING THEN 1 ELSE 0 END)) "
			+ "FROM Order o WHERE o.shipper.userName = :username GROUP BY o.shipper")
	Optional<ShipperRecord> findShipperRecordByShipperUsername(@Param("username") String username);

	@Query("SELECT new com.starshop.models.MonthlyReport("
			+ "YEAR(o.orderDate), MONTH(o.orderDate), SUM(o.finalTotal), SUM(o.profit)) "
			+ "FROM Order o " 
			+ "WHERE o.currentStatus = com.starshop.utils.OrderStatus.DELIVERED "
			+ "GROUP BY YEAR(o.orderDate), MONTH(o.orderDate) " 
			+ "ORDER BY YEAR(o.orderDate), MONTH(o.orderDate)")
	List<MonthlyReport> getMonthlyReport();
	
	@Query("SELECT new com.starshop.models.MonthlyShipperRecord(YEAR(o.updatedDate), MONTH(o.updatedDate), " +
	           "SUM(CASE WHEN o.currentStatus = com.starshop.utils.OrderStatus.DELIVERED THEN 1 ELSE 0 END), COUNT(o)) " +
	           "FROM Order o WHERE o.shipper.userName = :username " +
	           "AND YEAR(o.updatedDate) = :year " +
	           "GROUP BY YEAR(o.updatedDate), MONTH(o.updatedDate)"
	           + "ORDER BY YEAR(o.updatedDate), MONTH(o.updatedDate)")
	List<MonthlyShipperRecord> getMonthlyRecordByUsername(@Param("username") String username, 
	                                                     @Param("year") int year);
	
	@Query("SELECT SUM(o.finalTotal) FROM Order o " + 
			"WHERE o.currentStatus = com.starshop.utils.OrderStatus.DELIVERED")
	Long getTotalRevenue();
	
	Long countByCurrentStatus(OrderStatus status);

	@Query("SELECT SUM(o.profit) FROM Order o " + 
			"WHERE o.currentStatus = com.starshop.utils.OrderStatus.DELIVERED")
	Long getTotalProfit();
	
	Page<Order> findByUser(Customer customer, Pageable pageable);

    Page<Order> findByUserAndCurrentStatus(Customer customer, OrderStatus status, Pageable pageable);

    long countByUser(Customer customer);

    long countByUserAndCurrentStatus(Customer customer, OrderStatus status);

}
