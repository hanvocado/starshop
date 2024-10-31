package com.starshop.services.impl;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.starshop.models.AuthenticationRequest;
import com.starshop.models.AuthenticationResponse;
import com.starshop.models.IntrospectResponse;
import com.starshop.models.IntrospectToken;
import com.starshop.models.User;
import com.starshop.repositories.UserRepository;
import com.starshop.services.AuthenticationService;
import com.starshop.utils.Constants;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	UserRepository userRepository;

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		var user = userRepository.findByUserName(request.getUserName())
				.orElseThrow(() -> new RuntimeException("User not existed"));

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

		if (!authenticated)
			throw new RuntimeException("Unauthenticated");
		
		var token = generateToken(request.getUserName());

		return AuthenticationResponse.builder().token(token).build();
	}
	
	@Override
	public IntrospectResponse introspect(IntrospectToken request) throws JOSEException, ParseException {
		var token = request.getToken();
		
		JWSVerifier verifier = new MACVerifier(Constants.SIGNER_KEY.getBytes());
		
		SignedJWT signedJWT = SignedJWT.parse(token);
		Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
		
		var verified = signedJWT.verify(verifier);
		
		return IntrospectResponse.builder()
				.valid(verified && expiryTime.after(new Date()))
				.build();
	}


	private String generateToken(String userName) {
		JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

		JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder().subject(userName).issuer("starshop.com")
				.issueTime(new Date()).expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
				.build();

		Payload payload = new Payload(jwtClaimsSet.toJSONObject());

		JWSObject jwsObject = new JWSObject(header, payload);

		try {
			jwsObject.sign(new MACSigner(Constants.SIGNER_KEY.getBytes()));
			return jwsObject.serialize();
		} catch (JOSEException e) {
			throw new RuntimeException(e);
		}
	}
}
