package com.gialongchuai.shopapp.services.impl;

import com.gialongchuai.shopapp.dtos.request.AuthenticationRequest;
import com.gialongchuai.shopapp.dtos.request.IntroSpectRequest;
import com.gialongchuai.shopapp.dtos.request.LogoutRequest;
import com.gialongchuai.shopapp.dtos.request.RefreshTokenRequest;
import com.gialongchuai.shopapp.dtos.response.AuthenticationResponse;
import com.gialongchuai.shopapp.dtos.response.IntroSpectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface IAuthenticationService {
    IntroSpectResponse introspect(IntroSpectRequest introSpectRequest) throws JOSEException, ParseException;
    void logout(LogoutRequest logoutRequest) throws ParseException, JOSEException;
    AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException;
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
