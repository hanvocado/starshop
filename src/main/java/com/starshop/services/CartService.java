package com.starshop.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.starshop.entities.Cart;
import com.starshop.entities.ProductLine;

public interface CartService {

	void removeFromCart(UUID userId, Long productLineId);

	void updateCartItem(UUID userId, Long productId, int quantity);

	Cart getCartByUserId(UUID userId);

	Optional<List<ProductLine>> getProductLines(UUID userId);
	
	void addToCart(UUID userId, Long productId, int quantity);

	ProductLine updateProductLineQuantity(Long productLineId, int change);

	double calculateProductLinePrice(Long cartId);

}
