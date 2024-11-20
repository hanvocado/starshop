package com.starshop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.starshop.entities.AuthenticationResponse;
import com.starshop.entities.User;
import com.starshop.models.UserLogin;
import com.starshop.repositories.UserRepository;
import com.starshop.services.AuthService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Override
	public Authentication authenticate(UserLogin userLogin) {
		log.warn("userLogin username: {}", userLogin.getUsername());
		log.warn("userLogin pass :{}", userLogin.getPassword());
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getUsername(),
                        userLogin.getPassword()
                )
        );
    }
}
