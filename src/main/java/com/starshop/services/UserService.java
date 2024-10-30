package com.starshop.services;

import java.util.List;
import java.util.Optional;

import com.starshop.models.Product;
import com.starshop.models.User;

public interface UserService {

	List<Product> getWishlist(Long userId);

	void addProductToWishlist(Long userId, Long productId);

	Optional<User> findById(Long id);
}
