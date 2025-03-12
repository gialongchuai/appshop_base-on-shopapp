package com.gialongchuai.shopapp.services;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

import com.gialongchuai.shopapp.services.impl.IAuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gialongchuai.shopapp.dtos.request.AuthenticationRequest;
import com.gialongchuai.shopapp.dtos.request.IntroSpectRequest;
import com.gialongchuai.shopapp.dtos.request.LogoutRequest;
import com.gialongchuai.shopapp.dtos.request.RefreshTokenRequest;
import com.gialongchuai.shopapp.dtos.response.AuthenticationResponse;
import com.gialongchuai.shopapp.dtos.response.IntroSpectResponse;
import com.gialongchuai.shopapp.entities.InvalidatedToken;
import com.gialongchuai.shopapp.entities.User;
import com.gialongchuai.shopapp.exceptions.SecurityErrorCode;
import com.gialongchuai.shopapp.exceptions.UserErrorCode;
import com.gialongchuai.shopapp.exceptions.custom.AppException;
import com.gialongchuai.shopapp.repositories.InvalidatedTokenRepository;
import com.gialongchuai.shopapp.repositories.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService implements IAuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    InvalidatedTokenRepository invalidatedTokenRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    private String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    private long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    private long REFRESHABLE_DURATION;

    @Override
    public IntroSpectResponse introspect(IntroSpectRequest introSpectRequest) throws JOSEException, ParseException {
        String token = introSpectRequest.getToken();

        boolean isValid = true;

        try {
            verifyToken(token, false);
        } catch (AppException appException) {
            isValid = false;
        }

        return IntroSpectResponse.builder().valid(isValid).build();
    }

    private SignedJWT verifyToken(String token, boolean isValid) throws JOSEException, ParseException {
        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isValid)
                ? new Date(signedJWT
                        .getJWTClaimsSet()
                        .getIssueTime()
                        .toInstant()
                        .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS)
                        .toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean verified = signedJWT.verify(jwsVerifier);

        if (!(verified && expiryTime.after(new Date()))) throw new AppException(SecurityErrorCode.UNAUTHENTICATED);

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(SecurityErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }

    @Override
    public void logout(LogoutRequest logoutRequest) throws ParseException, JOSEException {
        SignedJWT signToken = verifyToken(logoutRequest.getToken(), true);

        String jti = signToken.getJWTClaimsSet().getJWTID();

        Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jti).expiryTime(expiryTime).build();

        invalidatedTokenRepository.save(invalidatedToken);
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
        SignedJWT signedJWT = verifyToken(request.getToken(), true);

        String jti = signedJWT.getJWTClaimsSet().getJWTID();

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jti).expiryTime(expiryTime).build();

        invalidatedTokenRepository.save(invalidatedToken);

        var userId = signedJWT.getJWTClaimsSet().getSubject();

        var user =
                userRepository.findById(userId).orElseThrow(() -> new AppException(SecurityErrorCode.UNAUTHENTICATED));

        String token = generateToken(user);

        return AuthenticationResponse.builder().authenticated(true).token(token).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        User user = userRepository
                .findByPhoneNumber(authenticationRequest.getPhoneNumber())
                .orElseThrow(() -> new AppException(UserErrorCode.USER_NOT_EXISTED));

        if (!user.getIsActive()) {
            throw new AppException(UserErrorCode.USER_IS_LOCKED);
        }

        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new AppException(SecurityErrorCode.UNAUTHENTICATED);
        }

        String token = generateToken(user);

        return AuthenticationResponse.builder().authenticated(true).token(token).build();
    }

    private String generateToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getId()) // ở đây để sub là username để qua bên kia getInfo thì lấy cái sub này
                // mà tra
                .issuer("gialongchuai.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user)) // dùng enum USER mặc định để build scope
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token!");
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (user.getRole() != null) {
            stringJoiner.add("ROLE_" + user.getRole().getName());
        }
        return stringJoiner.toString();
    }
}
