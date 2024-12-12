package com.starshop.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starshop.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID>{

}
