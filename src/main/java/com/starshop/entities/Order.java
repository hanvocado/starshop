package com.starshop.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import com.starshop.models.OrderStatus;
import com.starshop.models.PaymentType;
import com.starshop.utils.Converter;

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
    private Set<ProductLine> lines;
	
	@Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;
	
	@Column(name = "deliver_date", nullable = true)
    private LocalDateTime deliverDate;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;
	
	@Column(name = "total_amount", nullable = false)
    private double totalAmount;
	
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
}
