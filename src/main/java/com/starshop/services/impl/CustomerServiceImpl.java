package com.starshop.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starshop.entities.Address;
import com.starshop.entities.Customer;
import com.starshop.entities.Voucher;
import com.starshop.repositories.AddressRepository;
import com.starshop.repositories.CustomerRepository;
import com.starshop.repositories.VoucherRepository;
import com.starshop.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired 
	private VoucherRepository voucherRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
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
}
