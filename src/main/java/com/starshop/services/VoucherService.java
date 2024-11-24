package com.starshop.services;


import java.util.List;

import org.springframework.data.domain.Page;

import com.starshop.entities.Voucher;

public interface VoucherService {
	Page<Voucher> getAll(Integer pageNo, Integer pageSize, String search);
	
	Page<Voucher> getUnexpired(Integer pageNo, Integer pageSize);
	
	Page<Voucher> getExpired(Integer pageNo, Integer pageSize);
		
	void save(Voucher voucher);
	
	Voucher findByCode(String code);

	void delete(String code);

	List<Voucher> getFreeshipVoucher();

	List<Voucher> getDiscountVoucher();
}
