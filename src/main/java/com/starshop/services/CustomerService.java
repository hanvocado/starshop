package com.starshop.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.starshop.entities.Address;
import com.starshop.entities.Customer;

public interface CustomerService {

	void addVoucherToCustomer(UUID userId, String voucherCode);

	List<Address> getOtherAddresses(Customer customer);

	Optional<Address> getDefaultAddress(Customer customer);

}
