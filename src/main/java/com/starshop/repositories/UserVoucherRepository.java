package com.starshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starshop.models.UserVoucher;

public interface UserVoucherRepository extends JpaRepository<UserVoucher, String> {

}
