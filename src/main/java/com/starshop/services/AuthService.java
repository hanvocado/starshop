package com.starshop.services;

import com.starshop.entities.User;
import com.starshop.models.UserLogin;

public interface AuthService {

	User authenticate(UserLogin userLogin);

}
