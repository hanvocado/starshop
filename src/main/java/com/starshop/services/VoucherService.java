package com.starshop.services;


import org.springframework.data.domain.Page;

import com.starshop.models.Voucher;

public interface VoucherService {
	Page<Voucher> getAll(Integer pageNo, Integer pageSize, String search);
	
	Page<Voucher> getUnexpired(Integer pageNo, Integer pageSize);
	
	Page<Voucher> getExpired(Integer pageNo, Integer pageSize);
		
	void save(Voucher voucher);
}
