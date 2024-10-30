package com.starshop.services;

import java.util.List;

import com.starshop.models.Product;

public interface UserService {

	List<Product> getWishlist(Long userId);

	void addProductToWishlist(Long userId, Long productId);

}
