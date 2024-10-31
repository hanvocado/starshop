package com.starshop.services;

import com.starshop.models.AuthenticationRequest;

public interface AuthenticationService {

	boolean authenticate(AuthenticationRequest request);

}
