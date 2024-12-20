package com.starshop.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.starshop.entities.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
	// Find all unexpired vouchers
    Page<Voucher> findByExpiredAtAfter(Pageable pageable, LocalDateTime localDateTime);
    
    Page<Voucher> findByIsFreeshipTrueAndExpiredAtAfter(Pageable pageable, LocalDateTime localDateTime);
    
    Page<Voucher> findByIsFreeshipFalseAndExpiredAtAfter(Pageable pageable, LocalDateTime localDateTime);

    // Find all expired vouchers
    Page<Voucher> findByExpiredAtBefore(Pageable pageable, LocalDateTime localDateTime);

    Page<Voucher> findByIsFreeshipTrueAndExpiredAtBefore(Pageable pageable, LocalDateTime localDateTime);

    Page<Voucher> findByIsFreeshipFalseAndExpiredAtBefore(Pageable pageable, LocalDateTime localDateTime);
 
    
    Page<Voucher> findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(Pageable pageable, String name, String code);
    
    Voucher findByCode(String code);
    
    @Query("SELECT v FROM Voucher v WHERE v.expiredAt > :now AND v.isFreeship = false AND v NOT IN :usedVouchers")
    List<Voucher> findAvailableDiscountVouchers(@Param("now") LocalDateTime now, @Param("usedVouchers") List<Voucher> usedVouchers);
    
    @Query("SELECT v FROM Voucher v WHERE v.expiredAt > :now AND v.isFreeship = true AND v NOT IN :usedVouchers")
    List<Voucher> findAvailableFreeShipVouchers(@Param("now") LocalDateTime now, @Param("usedVouchers") List<Voucher> usedVouchers);



}
