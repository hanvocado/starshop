package com.starshop.services.impl;

import java.security.Key;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.starshop.entities.AuthenticationResponse;
import com.starshop.entities.User;
import com.starshop.models.UserLogin;
import com.starshop.repositories.UserRepository;
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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtServiceImpl implements JwtService {

//	@Override
//	public String generateToken(User user) {
//		JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
//
//		JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder().subject(user.getEmail()).issuer("starshop.com")
//				.issueTime(new Date()).expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
//				.claim("scope", user.getRole().getName())
//				.build();
//
//		Payload payload = new Payload(jwtClaimsSet.toJSONObject());
//
//		JWSObject jwsObject = new JWSObject(header, payload);
//
//		try {
//			jwsObject.sign(new MACSigner(Constants.SIGNER_KEY.getBytes()));
//			return jwsObject.serialize();
//		} catch (JOSEException e) {
//			throw new RuntimeException(e);
//		}
//	}
//	
//	@Override
//	public boolean validateToken(String token) throws ParseException, JOSEException {
//		JWSVerifier verifier = new MACVerifier(Constants.SIGNER_KEY.getBytes());
//
//		SignedJWT signedJWT = SignedJWT.parse(token);
//		Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
//
//		var verified = signedJWT.verify(verifier);
//
//		return verified && expiryTime.after(new Date());
//	}

//	 @Override
//	public String generateToken(String email) {
//	        // Prepare claims for the token
//	        Map<String, Object> claims = new HashMap<>();
//
//	        // Build JWT token with claims, subject, issued time, expiration time, and signing algorithm
//	        return Jwts.builder()
//	                .setClaims(claims)
//	                .setSubject(email)
//	                .setIssuedAt(new Date(System.currentTimeMillis()))
//	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) 
//	                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
//	    }
//
//
//	    // Creates a signing key from the base64 encoded secret.
//	    //returns a Key object for signing the JWT.
//	    @Override
//		public Key getSignKey() {
//	        // Decode the base64 encoded secret key and return a Key object
//	        byte[] keyBytes = Decoders.BASE64.decode(Constants.SIGNER_KEY);
//	        return Keys.hmacShaKeyFor(keyBytes);
//	    }
//
//
//	    // Extracts the userName from the JWT token.
//	    //return -> The userName contained in the token.
//	    @Override
//		public String extractEmail(String token) {
//	        // Extract and return the subject claim from the token
//	        return extractClaim(token, Claims::getSubject);
//	    }
//
//
//	    // Extracts the expiration date from the JWT token.
//	    //@return The expiration date of the token.
//	    @Override
//		public Date extractExpiration(String token) {
//	        // Extract and return the expiration claim from the token
//	        return extractClaim(token, Claims::getExpiration);
//	    }
//
//
//	    // Extracts a specific claim from the JWT token.
//	    // claimResolver A function to extract the claim.
//	    // return-> The value of the specified claim.
//	    @Override
//		public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
//	        // Extract the specified claim using the provided function
//	        final Claims claims = extractAllClaims(token);
//	        return claimResolver.apply(claims);
//	    }
//
//	    //Extracts all claims from the JWT token.
//	    //return-> Claims object containing all claims.
//	    @Override
//		public Claims extractAllClaims(String token) {
//	        // Parse and return all claims from the token
//	        return Jwts.parserBuilder()
//	                .setSigningKey(getSignKey())
//	                .build().parseClaimsJws(token).getBody();
//	    }
//
//
//	    //Checks if the JWT token is expired.
//	    //return-> True if the token is expired, false otherwise.
//	    @Override
//		public Boolean isTokenExpired(String token) {
//	        // Check if the token's expiration time is before the current time
//	        return extractExpiration(token).before(new Date());
//	    }
//
//	    //Validates the JWT token against the UserDetails.
//	    //return-> True if the token is valid, false otherwise.
//
//	    @Override
//		public Boolean validateToken(String token, UserDetails userDetails) {
//	        // Extract username from token and check if it matches UserDetails' username
//	        final String email = extractEmail(token);
//	        // Also check if the token is expired
//	        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
//	    }

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;

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
	        // Parse JWT
	        SignedJWT signedJWT = SignedJWT.parse(token);

	        // Xác thực chữ ký
	        JWSVerifier verifier = new MACVerifier(Constants.SIGNER_KEY.getBytes());

	        if (!signedJWT.verify(verifier)) {
	            throw new RuntimeException("Invalid JWT signature");
	        }

	        // Trả về claims set nếu xác thực thành công
	        return signedJWT.getJWTClaimsSet();
	    } catch (JOSEException | ParseException e) {
	        throw new RuntimeException("Error parsing or verifying the token", e);
	    }
	}

	public Key getSignKey() {
	    // Giải mã khóa từ Base64 để sử dụng cho HS512
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
