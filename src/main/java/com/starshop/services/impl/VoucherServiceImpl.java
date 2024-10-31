package com.starshop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private VoucherRepository voucherRepo;

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

}
