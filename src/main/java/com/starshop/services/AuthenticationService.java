package com.starshop.services;

import java.text.ParseException;

import com.nimbusds.jose.JOSEException;
import com.starshop.models.AuthenticationRequest;
import com.starshop.models.AuthenticationResponse;
import com.starshop.models.IntrospectResponse;
import com.starshop.models.IntrospectToken;

public interface AuthenticationService {

	AuthenticationResponse authenticate(AuthenticationRequest request);

	IntrospectResponse introspect(IntrospectToken request) throws JOSEException, ParseException;
}
