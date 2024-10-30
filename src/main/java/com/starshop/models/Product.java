package com.starshop.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;
	
	@Column(length=50, nullable = false)
	private String name;
	
	private String description;
	
	@Column(nullable = false)
	private float weight;
	
	@Column(name = "cost_price", nullable = false)
	private double costPrice;
	
	@Column(name = "sale_price", nullable = false)
	private double salePrice;
	
	@Column(name = "discount_percent", columnDefinition = "int default 0")
	private int discountPercent;
		
	@Column(name = "current_quantity")
	private int currentQuantity;
	
	@Lob
	private String image;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = true)
	private Category category;
	
	@Column(name = "is_published", columnDefinition = "boolean default true")
	private boolean isPublished;
	
	@Transient	
	public double getDisplayPrice() {
		return (salePrice - salePrice * discountPercent / 100);
	}
	
	@Transient	
	public double getProfit() {
		return salePrice - getDisplayPrice();
	}
}
