package com.starshop.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starshop.entities.Customer;
import com.starshop.entities.Voucher;
import com.starshop.repositories.CustomerRepository;
import com.starshop.repositories.VoucherRepository;
import com.starshop.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired 
	private VoucherRepository voucherRepository;
	
	@Override
	public void addVoucherToCustomer(UUID userId, String voucherCode) {
        Customer customer = customerRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Voucher voucher = voucherRepository.findByCode(voucherCode);

        customer.getUsedVouchers().add(voucher);
        customerRepository.save(customer);
    }
}
