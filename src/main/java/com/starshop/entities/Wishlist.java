package com.starshop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.starshop.entities.compositekeys.WishlistKey;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wishlist {
	@EmbeddedId
    WishlistKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
    
    @Column(name = "is_active")
    private Boolean isActive;

	public Wishlist(User user, Product product) {
		this.user = user;
		this.product = product;
	}
    
}

