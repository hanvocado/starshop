package com.starshop.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.naming.AuthenticationException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.starshop.entities.Product;
import com.starshop.entities.User;
import com.starshop.models.UserLogin;
import com.starshop.models.ViewMessage;
import com.starshop.utils.RoleName;

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

	String getUserRole(Authentication authentication);

	User getUserByAuthentication() throws AuthenticationException;

	User getUserByUserName(String userName);


//	UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

}
