package com.starshop.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.starshop.entities.Product;
import com.starshop.entities.Role;
import com.starshop.entities.User;
import com.starshop.entities.Wishlist;
import com.starshop.models.UserLogin;
import com.starshop.models.ViewMessage;
import com.starshop.repositories.ProductRepository;
import com.starshop.repositories.RoleRepository;
import com.starshop.repositories.UserRepository;
import com.starshop.repositories.WishlistRepository;
import com.starshop.services.UserService;
import com.starshop.utils.Constants;
import com.starshop.utils.RoleName;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

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
	public boolean addUser(User user) {
		if (userRepository.existsByUserName(user.getUserName()) || userRepository.existsByEmail(user.getEmail())) {
			return false;
		}

		assignRole(user, RoleName.USER.name());
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		userRepository.save(user);
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
	public void assignRole(User user, String roleName) {
		Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found"));
		user.setRole(role);
	}

//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		Optional<User> user = userRepository.findByEmail(email);
//        return user.map(UserLogin::new)
//                .orElseThrow(() -> new UsernameNotFoundException("Email not found: " + email));
//	}
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	    User user = userRepository.findByUserName(username)
//	            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//	    
//	    Collection<GrantedAuthority> authorities = new ArrayList<>();
//	    authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
//
//	    return new org.springframework.security.core.userdetails.User(
//	            user.getUserName(),
//	            user.getPassword(),
//	            authorities
//	    );
//	}


}
