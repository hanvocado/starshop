package com.starshop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.starshop.entities.Customer;
import com.starshop.entities.User;
import com.starshop.entities.Voucher;
import com.starshop.repositories.CustomerRepository;
import com.starshop.repositories.VoucherRepository;
import com.starshop.services.VoucherService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class VoucherServiceImpl implements VoucherService {
	@Autowired
	private VoucherRepository voucherRepo;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Page<Voucher> getAll(Integer pageNo, Integer pageSize, String search) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		if (search == null || search.isBlank())
			return voucherRepo.findAll(pageable);
		else
			return voucherRepo.findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(pageable, search, search);
	}

	@Override
	public Page<Voucher> getUnexpired(Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		LocalDateTime now = LocalDateTime.now();
		return voucherRepo.findByExpiredAtAfter(pageable, now);
	}

	@Override
	public Page<Voucher> getExpired(Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		LocalDateTime now = LocalDateTime.now();
		return voucherRepo.findByExpiredAtAfter(pageable, now);
	}

	@Override
	public void save(Voucher voucher) {
		voucherRepo.save(voucher);
	}

	@Override
	public Voucher findByCode(String code) {
		return voucherRepo.findByCode(code);
	}

	@Override
	public void delete(String code) {
		Voucher voucher = voucherRepo.findByCode(code);
		if (voucher != null)
			voucherRepo.delete(voucher);
	}
	
	@Override
	public List<Voucher> getAvailableDiscountVouchers(UUID customerId) {
	    Customer customer = customerRepository.findById(customerId)
	            .orElseThrow(() -> new RuntimeException("Customer not found"));
	    List<Voucher> usedVouchers = customer.getUsedVouchers();
	    LocalDateTime now = LocalDateTime.now();
	    return voucherRepo.findAvailableDiscountVouchers(now, usedVouchers);
	}

	@Override
	public List<Voucher> getAvailableFreeShipVouchers(UUID customerId) {
	    Customer customer = customerRepository.findById(customerId)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	    List<Voucher> usedVouchers = customer.getUsedVouchers();
	    LocalDateTime now = LocalDateTime.now();
	    return voucherRepo.findAvailableFreeShipVouchers(now, usedVouchers);
	}


}
