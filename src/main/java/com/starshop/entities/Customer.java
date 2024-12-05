package com.starshop.entities;

import java.util.List;

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
	private int defaultAddressId;
	
	@OneToMany(mappedBy = "user")
	private List<Wishlist> wishlists;
	
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Cart cart;
	
	@ManyToMany()
    @JoinTable(
        name = "user_voucher",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "voucher_id")
    )
    private List<Voucher> usedVouchers;
	
	@Override
    public String getRole() {
        return RoleName.CUSTOMER.name();
    }
}
