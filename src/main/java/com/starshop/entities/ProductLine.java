package com.starshop.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productlines")
public class ProductLine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	
	@NotNull(message = "Số lượng không được để trống")
	private int quantity;
}
