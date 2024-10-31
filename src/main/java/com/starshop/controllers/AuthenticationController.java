package com.starshop.controllers;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;
import com.starshop.models.AuthenticationRequest;
import com.starshop.models.AuthenticationResponse;
import com.starshop.models.IntrospectResponse;
import com.starshop.models.IntrospectToken;
import com.starshop.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	@Autowired
	AuthenticationService authenticationService;

	@PostMapping("/token")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		var result = authenticationService.authenticate(request);
		/*
		 * if (result) { return ResponseEntity.ok("Authentication successful"); } else {
		 * return ResponseEntity.status(401).body("Invalid password"); }
		 */
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/introspect")
	public ResponseEntity<IntrospectResponse> authenticate(@RequestBody IntrospectToken request) throws JOSEException, ParseException {
		var result = authenticationService.introspect(request);
		/*
		 * if (result) { return ResponseEntity.ok("Authentication successful"); } else {
		 * return ResponseEntity.status(401).body("Invalid password"); }
		 */
		return ResponseEntity.ok(result);
	}

}
