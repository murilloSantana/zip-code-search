package com.zipcode.zipcodesearch.address.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.zipcode.zipcodesearch.security.model.ApiKeyAuthenticationToken;
import com.zipcode.zipcodesearch.security.service.JWTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JWTServiceTest {

    @InjectMocks
    private JWTService jwtService;

    private String secretKey = "2345678";

    @BeforeEach
    public void setUp() {
        jwtService.setSecretKey(secretKey);
    }

    private DecodedJWT decodeToken(String authorizationToken) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();

        return verifier.verify(authorizationToken);
    }

    private String mockToken(String apiKey, String email) {
        ApiKeyAuthenticationToken apiKeyAuthenticationToken =
                new ApiKeyAuthenticationToken(apiKey, email, Collections.emptyList());

        return jwtService.generateToken(apiKeyAuthenticationToken);
    }

    @Test
    public void testGenerateToken() {
        String apiKey = "12345678";
        String email = "teste.mock@gmail.com";

        ApiKeyAuthenticationToken apiKeyAuthenticationToken =
                new ApiKeyAuthenticationToken(apiKey, email, Collections.emptyList());

        String authorizationTokenResponse = jwtService.generateToken(apiKeyAuthenticationToken);

        DecodedJWT jwt = this.decodeToken(authorizationTokenResponse);

        assertEquals(apiKey, jwt.getClaim("apiKey").asString());
        assertEquals(email, jwt.getClaim("email").asString());
    }

    @Test
    public void testGetAuthenticationRequestByToken() {
        String apiKey = "12345678";
        String email = "teste.mock@gmail.com";

        ApiKeyAuthenticationToken apiKeyAuthenticationTokenExpected =
                new ApiKeyAuthenticationToken(apiKey, email, Collections.emptyList());

        ApiKeyAuthenticationToken authorizationTokenResponseActual = jwtService
                .getAuthenticationRequestByToken(this.mockToken(apiKey, email));

        assertEquals(apiKeyAuthenticationTokenExpected, authorizationTokenResponseActual);
    }

    @Test
    public void testGetAuthenticationRequestByTokenThrowError() {
        String apiKey = "12345678";
        String email = "teste.mock@gmail.com";

        String token = this.mockToken(apiKey, email);
        String invalidToken = token + "1";

        assertThrows(JWTVerificationException.class, () -> {
            jwtService.getAuthenticationRequestByToken(invalidToken);
        });

    }
}
