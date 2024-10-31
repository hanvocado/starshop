package com.starshop.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.starshop.models.Product;
import com.starshop.models.User;

public interface UserService {

	Optional<User> getUserById(UUID id);

	List<User> getAllUsers();

	void deleteUser(UUID id);

	User updateUser(UUID id, User updatedUser);

	User addUser(User user);

	List<Product> getWishlist(UUID userId);

	void addProductToWishlist(UUID userId, Long productId);

}
