package com.starshop.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starshop.entities.Shipper;

public interface ShipperRepository extends JpaRepository<Shipper, UUID> {

}
