package com.starshop.models;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vouchers")
public class Voucher {
	@Id
	@Column(name = "voucher_code", length = 20)	
	private String code;

	@Column(length = 200, nullable = false)
	private String name;

	private String description;

	@Column(name = "expired_at", nullable = false)
	private LocalDateTime expiredAt;

	@Column(name = "discount_percent", columnDefinition = "int default 5")
	private int discountPercent;

	@Column(name = "max_discount_amount", columnDefinition = "int default 20000")
	private int maxDiscountAmount;

	@Column(name = "is_freeship", columnDefinition = "boolean default false")
	private boolean isFreeship;
	
	@Column(name = "min_items_total", columnDefinition = "int default 0")
	private int minOrderItemsTotal; // Minimum order value required to apply this voucher

	/*
	 * @Transient public double getDiscountAmount(Order order) { if
	 * (this.isAppliable(order)) { double originPrice; if (this.isFreeship()) {
	 * originPrice = order.getShippingFee(); } else { originPrice =
	 * order.getItemsTotal(); }
	 * 
	 * double discountValue = originPrice * this.discountPercent / 100; return
	 * Math.min(discountValue, this.maxDiscountAmount); } return 0; }
	 * 
	 * @Transient public boolean isAppliable(Order order) { if
	 * (order.getItemsTotal() < this.minOrderItemsTotal) { return false; }
	 * 
	 * return true; }
	 */
}
