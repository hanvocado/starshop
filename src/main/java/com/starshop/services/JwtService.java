package com.starshop.services;

import java.security.Key;
import java.security.Principal;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.nimbusds.jwt.JWTClaimsSet;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

public interface JwtService {

	String getUserNameFromJwt(String token);

	String getRoleFromJwt(String token);

	Key getSignKey();

	Boolean isTokenExpired(JWTClaimsSet claimsSet);

	Boolean validateToken(String token, UserDetails userDetails);

	JWTClaimsSet extractAllClaims(String token);

	String getJwtFromCookies(HttpServletRequest request);

	String generateToken(Authentication authentication);

	UUID getUserIdFromPrincipal(Principal principal);

}
