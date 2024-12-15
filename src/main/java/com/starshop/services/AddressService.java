package com.starshop.services;

import com.starshop.entities.Address;
import com.starshop.entities.Customer;

public interface AddressService {

	Address save(Address entity);

	void setDefaultAddress(Customer customer, Long addressId);

	Address getDefaultAddress(Customer customer);

}
