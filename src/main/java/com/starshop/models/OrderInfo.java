package com.starshop.models;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.starshop.entities.Address;
import com.starshop.entities.Customer;
import com.starshop.entities.ProductLine;

import jakarta.persistence.Transient;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String voucherCode;
	private double freeShip;
	private double discount;
	private double finalPrice;
	private int shippingFee;

	@Transient
	private List<ProductLine> selectedProductLines;
	private Address address;
	private Customer customer;

	public double getSubtotal() {
		if (selectedProductLines == null || selectedProductLines.isEmpty()) {
			return 0.0;
		}
		return selectedProductLines.stream().mapToDouble(ProductLine::getSubTotal).sum();
	}

}
