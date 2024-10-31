package com.starshop.repositories;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.starshop.models.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
	// Find all unexpired vouchers
    Page<Voucher> findByExpiredAtAfter(Pageable pageable, LocalDateTime localDateTime);

    Page<Voucher> findByIsFreeshipTrueAndExpiredAtAfter(Pageable pageable, LocalDateTime localDateTime);
    
    Page<Voucher> findByIsFreeshipFalseAndExpiredAtAfter(Pageable pageable, LocalDateTime localDateTime);

    // Find all expired vouchers
    Page<Voucher> findByExpiredAtBefore(Pageable pageable, LocalDateTime localDateTime);

    Page<Voucher> findByIsFreeshipTrueAndExpiredAtBefore(Pageable pageable, LocalDateTime localDateTime);

    Page<Voucher> findByIsFreeshipFalseAndExpiredAtBefore(Pageable pageable, LocalDateTime localDateTime);
}
