package com.starshop.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
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
	private Customer user;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductLine> lines = new ArrayList<>(); 

	@Column(name = "order_date", nullable = false)
	private LocalDateTime orderDate;

	@Column(name = "updated_date", nullable = true)
	private LocalDateTime updatedDate;

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
	private Shipper shipper;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TrackingOrder> tracking = new ArrayList<>();
	
	private OrderStatus currentStatus;
	
	private double profit;
	
	public Order(Customer user, List<ProductLine> lines, double shippingFee, Voucher voucher, PaymentType paymentType, String note) {
		this.user = user;
		this.lines = lines;
		this.shippingFee = shippingFee;
	}
	
	public String getFormattedOrderDate() {
		return Converter.localDateTimeToDateWithSlash(orderDate);
	}

	public void setTotalAndProfit() {
		setProductsTotal();
		this.finalTotal = productsTotal + shippingFee - this.getDiscountTotal();
		double profit = 0;
		for (ProductLine line : this.lines) {
			profit += line.getProfit();
		}
		this.profit = profit;
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

	public void addProductLine(ProductLine newLine) {
		lines.add(newLine);
		newLine.setOrder(this);
	}

	public void removeProductLine(ProductLine line) {
		lines.remove(line);
		line.setOrder(null);
	}
	
	public void addTrackingOrder(TrackingOrder trackingOrder) {
		tracking.add(trackingOrder);
		trackingOrder.setOrder(this);
		updatedDate = LocalDateTime.now();
		this.currentStatus = trackingOrder.getStatus();
	}

	public void removeTrackingOrder(TrackingOrder trackingOrder) {
		tracking.remove(trackingOrder);
		trackingOrder.setOrder(null);
	}
	
	public int getNumberOfProducts() {
		return this.lines.size();
	}

    public long getTotalItems() {
    	long res = 0;
		for (ProductLine line : this.lines) {
			res += line.getQuantity();
		}
		return res;
    }
}
