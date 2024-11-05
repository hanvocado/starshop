package com.starshop.services;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtService {

	String generateToken(Authentication authentication);

//	Boolean validateToken(String token, UserDetails userDetails);
//
//	Boolean isTokenExpired(String token);
//
//	Claims extractAllClaims(String token);
//
//	<T> T extractClaim(String token, Function<Claims, T> claimResolver);
//
//	Date extractExpiration(String token);
//
//	String extractEmail(String token);
//
//	Key getSignKey();
//
//	String generateToken(String email);
	
	

}
