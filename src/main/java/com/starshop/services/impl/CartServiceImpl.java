package com.starshop.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starshop.entities.Cart;
import com.starshop.entities.Product;
import com.starshop.entities.ProductLine;
import com.starshop.entities.User;
import com.starshop.repositories.CartRepository;
import com.starshop.repositories.ProductLineRepository;
import com.starshop.repositories.ProductRepository;
import com.starshop.repositories.UserRepository;
import com.starshop.services.CartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
	private final CartRepository cartRepository;
	private final ProductLineRepository productLineRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;

	@Override
	public Cart getCartByUserId(UUID userId) {
		return cartRepository.findByUserId(userId)
				.orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));
	}

	@Override
	public void addToCart(UUID userId, Long productId, int quantity) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		Cart cart = cartRepository.findByUser(user);

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		ProductLine productLine = productLineRepository.findByCartAndProduct(cart, product);
		if (productLine == null) {
			productLine = new ProductLine(product, cart, 0);
		}

		// Cập nhật số lượng sản phẩm trong giỏ hàng
		productLine.setQuantity(productLine.getQuantity() + quantity);

		// Lưu lại ProductLine vào cơ sở dữ liệu
		productLineRepository.save(productLine);
	}

	@Override
	public void updateCartItem(UUID userId, Long productId, int quantity) {
		Cart cart = getCartByUserId(userId);

		ProductLine productLine = productLineRepository.findByCartIdAndProductId(cart.getId(), productId)
				.orElseThrow(() -> new RuntimeException("Product not in cart"));
		productLine.setQuantity(quantity);
		productLineRepository.save(productLine);
	}

	@Override
	public void removeFromCart(UUID userId, Long productId) {
		Cart cart = getCartByUserId(userId);

		ProductLine productLine = productLineRepository.findByCartIdAndProductId(cart.getId(), productId)
				.orElseThrow(() -> new RuntimeException("Product not in cart"));
		productLineRepository.delete(productLine);
	}

	@Override
	public Optional<List<ProductLine>> getProductLines(UUID userId) {
		Cart cart = cartRepository.findByUserId(userId).orElse(null);

		if (cart == null || cart.getProductLines() == null || cart.getProductLines().isEmpty()) {
			return Optional.of(Collections.emptyList());
		}

		return Optional.of(cart.getProductLines());
	}

	@Override
	public ProductLine updateProductLineQuantity(Long productLineId, int change) {
		ProductLine productLine = productLineRepository.findById(productLineId)
				.orElseThrow(() -> new RuntimeException("ProductLine not found"));
		int newQuantity = productLine.getQuantity() + change;
		productLine.setQuantity(newQuantity);
		
		if (newQuantity > 0 || productLine.getQuantity() > 0) {
			productLineRepository.save(productLine);
		} else {
			throw new RuntimeException("Quantity cannot be less than 1");
		}
		return productLine;
	}

	@Override
	public double calculateProductLinePrice(Long cartId) {
		return productLineRepository.findByCartId(cartId).stream()
				.mapToDouble(pl -> pl.getQuantity() * pl.getProduct().getDisplayPrice()).sum();
	}

}
