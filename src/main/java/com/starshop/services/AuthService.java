package com.starshop.services;

import org.springframework.security.core.Authentication;

import com.starshop.entities.User;
import com.starshop.models.UserLogin;

public interface AuthService {

	Authentication authenticate(UserLogin userLogin);

}
