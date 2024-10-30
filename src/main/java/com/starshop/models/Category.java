package com.starshop.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories", 
	uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id;
	
	@Column(length=50, nullable = false)
	private String name;
	
	@Column(name = "is_published", columnDefinition = "boolean default true")
	private boolean isPublished;
	
	@ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();
	
	public Category(String name) {
		this.name = name;
		this.isPublished = true;
	}
	
	public Category(String name, boolean isPublished) {
		this.name = name;
		this.isPublished = isPublished;
	}
}
