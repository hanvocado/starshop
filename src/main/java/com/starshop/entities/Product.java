package com.starshop.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Product implements Serializable {
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
	
	@Column(length = 500)
	private String image;

	@ManyToMany()
    @JoinTable(
        name = "product_categories",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();
	
	@Column(name = "is_published", columnDefinition = "boolean default true")
	private boolean isPublished;
	
	public double getDisplayPrice() {
		return (salePrice - salePrice * discountPercent / 100);
	}
	
	public double getProfit() {
		return salePrice - getDisplayPrice();
	}
	
	public String getCategoryNames() {
	    return categories.stream()
	                     .map(Category::getName)  // Assuming Category has a getName() method
	                     .collect(Collectors.joining(", "));
	}
	
	public void addCategory(Category category) {
        categories.add(category);
        category.getProducts().add(this);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.getProducts().remove(this);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name); // use only primary fields
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
               Objects.equals(name, product.name);
    }
}
