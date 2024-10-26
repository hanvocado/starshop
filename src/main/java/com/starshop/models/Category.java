package com.starshop.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories", 
	uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id;
	@Column(length=50, nullable = false)
	private String name;
	@Column(name = "is_deleted")
	private boolean isDeleted;
	@Column(name = "is_activated")
	private boolean isActivated;
	
	public Category(String name) {
		this.name = name;
		this.isActivated = true;
		this.isDeleted = false;
	}
	
	public Category(String name, boolean isActivated) {
		this.name = name;
		this.isActivated = isActivated;
		this.isDeleted = false;
	}
}
