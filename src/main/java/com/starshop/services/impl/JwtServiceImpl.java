package com.starshop.services.impl;

import java.security.Key;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.starshop.services.JwtService;
import com.starshop.utils.Constants;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtServiceImpl implements JwtService {

	@Override
	public String generateToken(Authentication authentication) {
		JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

		String scope = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" "));

		JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder().subject(authentication.getName()).issuer("starshop.com")
				.issueTime(new Date()).expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
				.claim("scope", scope).build();

		Payload payload = new Payload(jwtClaimsSet.toJSONObject());

		JWSObject jwsObject = new JWSObject(header, payload);

		try {
			jwsObject.sign(new MACSigner(Constants.SIGNER_KEY.getBytes()));
			return jwsObject.serialize();
		} catch (JOSEException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getJwtFromCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("jwt".equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	@Override
	public JWTClaimsSet extractAllClaims(String token) {
		try {
			SignedJWT signedJWT = SignedJWT.parse(token);
			JWSVerifier verifier = new MACVerifier(Constants.SIGNER_KEY.getBytes());

			if (!signedJWT.verify(verifier)) {
				throw new RuntimeException("Invalid JWT signature");
			}
			return signedJWT.getJWTClaimsSet();
		} catch (JOSEException | ParseException e) {
			throw new RuntimeException("Error parsing or verifying the token", e);
		}
	}

	@Override
	public Boolean validateToken(String token, UserDetails userDetails) {
		try {
			SignedJWT signedJWT = SignedJWT.parse(token);
			JWSVerifier verifier = new MACVerifier(Constants.SIGNER_KEY.getBytes());

			if (!signedJWT.verify(verifier)) {
				return false;
			}

			JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

			String userName = claimsSet.getSubject();

			return (userName.equals(userDetails.getUsername()) && !isTokenExpired(claimsSet));
		} catch (ParseException | JOSEException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean isTokenExpired(JWTClaimsSet claimsSet) {
		Date expirationTime = claimsSet.getExpirationTime();
		return expirationTime == null || expirationTime.before(new Date());
	}

	@Override
	public Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(Constants.SIGNER_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	@Override
	public String getRoleFromJwt(String token) {
		try {
			JWTClaimsSet claims = extractAllClaims(token);
			return claims.getStringClaim("scope");
		} catch (ParseException e) {
			throw new RuntimeException("Error extracting role from JWT", e);
		}
	}

	@Override
	public String getUserNameFromJwt(String token) {
		JWTClaimsSet claims = extractAllClaims(token);
		return claims.getSubject();
	}

}
