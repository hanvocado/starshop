package com.starshop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.starshop.models.AuthenticationRequest;
import com.starshop.repositories.UserRepository;
import com.starshop.services.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public boolean authenticate(AuthenticationRequest request) {
		var user = userRepository.findByUserName(request.getUserName())
				.orElseThrow(() -> new RuntimeException("User not existed"));
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		return passwordEncoder.matches(request.getPassword(), user.getPassword());
	}
}
