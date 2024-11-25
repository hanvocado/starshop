package com.starshop.entities;
import java.time.LocalDateTime;

import com.starshop.utils.OrderStatus;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.starshop.utils.Converter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "order_id", nullable = false)
	@ManyToOne
	private Order order;
	
	private OrderStatus status;
	
	@NotNull
	private LocalDateTime time;
	
	public TrackingOrder(Order order, OrderStatus status, LocalDateTime time) {
		this.order = order;
		this.status = status;
		this.time = time;
	}
	
	public String getFormattedTime() {
		return Converter.localDateTimeToDateWithSlash(this.time);
	}
	
}
