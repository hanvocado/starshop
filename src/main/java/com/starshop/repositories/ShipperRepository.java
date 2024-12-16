package com.starshop.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starshop.entities.Shipper;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, UUID> {
	List<Shipper> findByOrderByIsActiveDesc();
	
	List<Shipper> findByIsActiveTrue();
	
	Optional<Shipper> findByUserName(String username);
}
