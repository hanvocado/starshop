package com.starshop.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.starshop.utils.Converter;
import com.starshop.utils.OrderStatus;
import com.starshop.utils.PaymentType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductLine> lines;
	
	@Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;
	
	@Column(name = "deliver_date", nullable = true)
    private LocalDateTime deliverDate;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;
	
	@Column(name = "products_total", nullable = false)
	private double productsTotal;
	
	@Column(name = "shipping_fee", nullable = false)
	private double shippingFee;
	
	@OneToOne
	@JoinColumn(name = "voucher_id", nullable = true)
	private Voucher voucher;
	
	@Column(name = "final_total", nullable = false)
	private double finalTotal;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "pay_method", nullable = false)
    private PaymentType payMethod;
	
	@Column(name = "is_payed", nullable = false)
    private boolean isPayed;
	
	@Column(name = "note", columnDefinition = " NVARCHAR(500)")
    private String note;	
	
	@ManyToOne
	@JoinColumn(name = "shipper_id", nullable = true)
	private User shipper;

	@Transient
	public String getFormattedOrderDate() {
		return Converter.localDateTimeToDateWithSlash(orderDate);
	}
	
	public void setFinalTotal() {
		setProductsTotal();
		this.finalTotal = productsTotal + shippingFee - this.getDiscountTotal();
	}
	
	private void setProductsTotal() {
		double total = 0;
		for (ProductLine line : this.lines) {
			total += line.getSubTotal();
		}
		this.productsTotal = total;
	}
	
	public double getDiscountTotal() {
		return this.voucher == null ? 0 : voucher.getDiscountAmount(this);
	}
}
