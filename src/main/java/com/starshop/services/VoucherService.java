package com.starshop.services;


import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.starshop.entities.Voucher;

public interface VoucherService {
	Page<Voucher> getAll(Integer pageNo, Integer pageSize, String search);
	
	Page<Voucher> getUnexpired(Integer pageNo, Integer pageSize);
	
	Page<Voucher> getExpired(Integer pageNo, Integer pageSize);
		
	void save(Voucher voucher);
	
	Voucher findByCode(String code);

	void delete(String code);

	List<Voucher> getAvailableFreeShipVouchers(UUID customerId);

	List<Voucher> getAvailableDiscountVouchers(UUID customerId);
}
