package com.starshop.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.starshop.utils.RoleName;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("CUSTOMER")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends User {
	@Column(name = "default_address_id")
	private Long defaultAddressId;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses;

	@JsonManagedReference
	@OneToMany(mappedBy = "user")
	private List<Order> orders;

	@JsonManagedReference
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Cart cart;

	@ManyToMany()
	@JoinTable(name = "user_voucher", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "voucher_id"))
	private List<Voucher> usedVouchers;
	
	@ManyToMany()
	@JoinTable(name = "wishlist", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> wishlist;

	@Override
	public String getRole() {
		return RoleName.CUSTOMER.name();
	}
}
