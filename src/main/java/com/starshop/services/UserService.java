package com.starshop.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.naming.AuthenticationException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.starshop.entities.Customer;
import com.starshop.entities.Product;
import com.starshop.entities.Shipper;
import com.starshop.entities.User;
import com.starshop.models.UserLogin;

public interface UserService {

	Optional<User> getUserById(UUID id);

	List<User> getAllUsers();

	void deleteUser(UUID id);

	User updateUser(UUID id, User updatedUser);

	boolean addCustomer(Customer user);

	Optional<User> findByEmail(String email);

	boolean checkUserLogin(UserLogin userLogin);

	String getUserRole(Authentication authentication);

	User getUserByAuthentication() throws AuthenticationException;

	User getUserByUserName(String userName);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
