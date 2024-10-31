package com.starshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starshop.models.AuthenticationRequest;
import com.starshop.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	@Autowired
	AuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
		boolean result = authenticationService.authenticate(request);
		if (result) {
			return ResponseEntity.ok("Authentication successful");
		} else {
			return ResponseEntity.status(401).body("Invalid password");
		}
	}

}
