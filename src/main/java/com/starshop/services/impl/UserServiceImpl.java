package com.starshop.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.starshop.models.Product;
import com.starshop.models.User;
import com.starshop.models.Wishlist;
import com.starshop.repositories.ProductRepository;
import com.starshop.repositories.UserRepository;
import com.starshop.repositories.WishlistRepository;
import com.starshop.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private WishlistRepository wishlistRepository;

	@Override
	public void addProductToWishlist(UUID userId, Long productId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		Wishlist wishlist = new Wishlist(user, product);
		wishlistRepository.save(wishlist);
	}

	@Override
	public List<Product> getWishlist(UUID userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		return wishlistRepository.findByUser(user).stream().map(Wishlist::getProduct).collect(Collectors.toList());
	}

	@Override
	public User addUser(User userRequest) {
		if (userRepository.existsByUserName(userRequest.getUserName())) {
			throw new RuntimeException("User existed!");
		}

		User user = new User();
		BeanUtils.copyProperties(userRequest, user);

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User updateUser(UUID id, User updatedUser) {
		return userRepository.findById(id).map(user -> {
			user.setUserName(updatedUser.getUserName());
			user.setPhoneNumber(updatedUser.getPhoneNumber());
			user.setEmail(updatedUser.getEmail());
			user.setPassword(updatedUser.getPassword());
			user.setProfileImg(updatedUser.getProfileImg());
			user.setRoleId(updatedUser.getRoleId());
			return userRepository.save(user);
		}).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng với ID: " + id));
	}

	@Override
	public void deleteUser(UUID id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserById(UUID id) {
		return userRepository.findById(id);
	}

}
