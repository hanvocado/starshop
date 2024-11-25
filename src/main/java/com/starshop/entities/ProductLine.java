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
	@JoinColumn(name = "order_id", nullable = true)
	private Order order;
	
	@NotNull(message = "Số lượng không được để trống")
	private int quantity;
	
	@ManyToOne
    @JoinColumn(name = "cart_id", nullable = true)
    private Cart cart;
	
	public ProductLine(Product product, Cart cart, int quantity) {
	    this.product = product;
	    this.cart = cart;
	    this.quantity = quantity;
	}
}
