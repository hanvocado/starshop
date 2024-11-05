package com.starshop.entities.compositekeys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class WishlistKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "user_id")
    UUID userId;

    @Column(name = "product_id")
    Long productId;
}
