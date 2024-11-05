package com.starshop.entities;

import com.starshop.entities.compositekeys.UserVoucherKey;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_vouchers")
public class UserVoucher {
	@EmbeddedId
    private UserVoucherKey id;
	
	@Column(name = "is_used", columnDefinition = "boolean default false")
    private boolean isUsed;
	
	@ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne
	@MapsId("voucherCode")
	@JoinColumn(name = "voucher_code")
	private Voucher voucher;

    public UserVoucher(User user, Voucher voucher) {
		this.user = user;
		this.voucher = voucher;
	}
}
