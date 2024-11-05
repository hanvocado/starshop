package com.starshop.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.starshop.models.Product;
import com.starshop.models.User;
import com.starshop.models.UserLogin;
import com.starshop.utils.RoleName;
import com.starshop.utils.ViewMessage;

public interface UserService {

	Optional<User> getUserById(UUID id);

	List<User> getAllUsers();

	void deleteUser(UUID id);

	User updateUser(UUID id, User updatedUser);

	List<Product> getWishlist(UUID userId);

	void addProductToWishlist(UUID userId, Long productId);

	boolean addUser(User user);

	Optional<User> findByEmail(String email);

	boolean checkUserLogin(UserLogin userLogin);

	void assignRole(User user, String roleName);


//	UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

}
