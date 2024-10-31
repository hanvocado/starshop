package com.starshop.models;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserVoucherKey implements Serializable {
	@Column(name = "voucher_code", length = 20)
    private String voucherCode;

    @Column(name = "user_id")
    private Long userId;
}
