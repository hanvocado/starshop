package com.starshop.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.starshop.entities.Address;
import com.starshop.entities.Customer;
import com.starshop.entities.Product;

public interface CustomerService {

	void addVoucherToCustomer(UUID userId, String voucherCode);

	List<Address> getOtherAddresses(Customer customer);

	Optional<Address> getDefaultAddress(Customer customer);

	void addToWishlist(Customer customer, Long productId);

	List<Product> getWishlist(String userName);

	void removeFromWishlist(Customer customer, Long productId);

}
