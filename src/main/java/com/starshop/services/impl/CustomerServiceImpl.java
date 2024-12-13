package com.starshop.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starshop.entities.Address;
import com.starshop.entities.Customer;
import com.starshop.entities.Product;
import com.starshop.entities.Voucher;
import com.starshop.repositories.AddressRepository;
import com.starshop.repositories.CustomerRepository;
import com.starshop.repositories.ProductRepository;
import com.starshop.repositories.VoucherRepository;
import com.starshop.services.CustomerService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired 
	private VoucherRepository voucherRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void addVoucherToCustomer(UUID userId, String voucherCode) {
        Customer customer = customerRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Voucher voucher = voucherRepository.findByCode(voucherCode);

        customer.getUsedVouchers().add(voucher);
        customerRepository.save(customer);
    }
	
    @Override
	public Optional<Address> getDefaultAddress(Customer customer) {
        return addressRepository.findById(customer.getDefaultAddressId());
    }

    @Override
	public List<Address> getOtherAddresses(Customer customer) {
        List<Address> allAddresses = addressRepository.findByCustomer(customer);
        return allAddresses.stream()
                .filter(address -> address.getId() != customer.getDefaultAddressId())
                .collect(Collectors.toList());
    }
    
    @Override
	public List<Product> getWishlist(String userName) {
        Customer customer = customerRepository.findByUserName(userName);
        if (customer == null) {
            throw new EntityNotFoundException("User not found!");
        }
        return customer.getWishlist();
    }
    
    @Override
	public void addToWishlist(Customer customer, Long productId) {
        
        Product product = productRepository.findById(productId)
        		.orElseThrow(() ->  new EntityNotFoundException("Product not found!"));

        if (customer.getWishlist().contains(product)) {
            throw new IllegalStateException("Product is already in your wishlist!");
        }

        customer.getWishlist().add(product);
        customerRepository.save(customer);
    }
    
    @Override
	public void removeFromWishlist(Customer customer, Long productId) {
    	Product product = productRepository.findById(productId)
        		.orElseThrow(() ->  new EntityNotFoundException("Product not found!"));

        List<Product> wishlist = customer.getWishlist();
        if (!wishlist.contains(product)) {
            throw new EntityNotFoundException("Product is not in your wishlist!");
        }

        wishlist.remove(product);
        customerRepository.save(customer);
    }
}
