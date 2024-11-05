package com.starshop.services;

import com.starshop.models.User;
import com.starshop.models.UserLogin;

public interface AuthService {

	User authenticate(UserLogin userLogin);

}
