package com.starshop.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.starshop.models.Voucher;
import com.starshop.repositories.VoucherRepository;
import com.starshop.services.VoucherService;
import java.time.LocalDateTime;

@Service
public class VoucherServiceImpl implements VoucherService {
	private VoucherRepository voucherRepo;

	@Override
	public Page<Voucher> getAll(Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return voucherRepo.findAll(pageable);
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

}
