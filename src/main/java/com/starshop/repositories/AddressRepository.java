package com.starshop.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starshop.entities.Address;
import com.starshop.entities.Customer;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	List<Address> findByCustomer(Customer customer);
	Optional<Address> findById(Long id);
	Optional<Address> findByIdAndCustomer(Long id, Customer customer);
}
