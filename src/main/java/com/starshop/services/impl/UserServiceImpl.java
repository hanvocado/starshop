package com.starshop.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.starshop.entities.*;
import com.starshop.models.UserLogin;
import com.starshop.repositories.*;
import com.starshop.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private WishlistRepository wishlistRepository;
	
	@Autowired
	private CartRepository cartRepository;

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
	public boolean addCustomer(Customer user) {
		if (userRepository.existsByUserName(user.getUserName()) || userRepository.existsByEmail(user.getEmail())) {
			return false;
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		
		Cart newCart = new Cart();
        newCart.setUser(user);
        cartRepository.save(newCart);
		
		return true;
	}

	@Override
	public boolean checkUserLogin(UserLogin userLogin) {
		return userRepository.findByUserName(userLogin.getUsername())
				.map(user -> passwordEncoder.matches(userLogin.getPassword(), user.getPassword())).orElse(false);
	}

	@Override
	public User updateUser(UUID id, User updatedUser) {
		return userRepository.findById(id).map(user -> {
			user.setUserName(updatedUser.getUserName());
			user.setPhoneNumber(updatedUser.getPhoneNumber());
			user.setEmail(updatedUser.getEmail());
			user.setPassword(updatedUser.getPassword());
			user.setProfileImg(updatedUser.getProfileImg());
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

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public String getUserRole(Authentication authentication) {
        UserLogin user = (UserLogin) authentication.getPrincipal();
        return user.getAuthorities()
                   .stream()
                   .findFirst() 
                   .map(GrantedAuthority::getAuthority) 
                   .orElseThrow(() -> new RuntimeException("No role found for user")); 
    }
	
	@Override
	public User getUserByAuthentication() throws AuthenticationException {
		
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (authentication == null) {
	        throw new AuthenticationException("No authentication found.");
	    }

	    String username = authentication.getName();

	    return userRepository.findByUserName(username)
	            .orElseThrow(() -> new NoSuchElementException("User not found with username: " + username));
	}
	
	@Override
	public User getUserByUserName(String userName) {
		return userRepository.findByUserName(userName)
				.orElseThrow(()-> new NoSuchElementException("User not found with username: " + userName));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(username);
        return user.map(UserLogin::new)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found: " + username));
	}

}
