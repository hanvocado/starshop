package com.starshop.services;

import com.starshop.entities.Address;
import com.starshop.entities.Customer;

public interface AddressService {

	<S extends Address> S save(S entity);

	void setDefaultAddress(Customer customer, Long addressId);

}
