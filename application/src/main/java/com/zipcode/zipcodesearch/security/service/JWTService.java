package com.zipcode.zipcodesearch.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.zipcode.zipcodesearch.security.model.ApiKeyAuthenticationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;

@Service
public class JWTService implements Serializable {

    private static final long serialVersionUID = -2120185165687788488L;

    public static final long JWT_TOKEN_TTL_IN_MINUTES = 60;

    @Value("${server.authentication.jwt.secret}")
    private String secretKey;

    private Date generateExpirationTime() {
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(JWT_TOKEN_TTL_IN_MINUTES);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private String addTokenPrefix(String token) {
        return "Bearer " + token;
    }

    private String removeTokenPrefix(String token) {
        return token.substring(7);
    }

    public String generateToken(ApiKeyAuthenticationToken apiKeyAuthenticationToken) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String authorizationToken = JWT.create()
                .withClaim("apiKey", apiKeyAuthenticationToken.getPrincipal().toString())
                .withClaim("email", apiKeyAuthenticationToken.getCredentials().toString())
                .withExpiresAt(this.generateExpirationTime())
                .withIssuer("auth0")
                .sign(algorithm);

        return this.addTokenPrefix(authorizationToken);
    }

    public ApiKeyAuthenticationToken getAuthenticationRequestByToken(String authorizationToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();

            DecodedJWT jwt = verifier.verify(this.removeTokenPrefix(authorizationToken));
            String apiKey = jwt.getClaim("apiKey").asString();
            String email = jwt.getClaim("email").asString();

            return new ApiKeyAuthenticationToken(apiKey, email, Collections.emptyList());
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Expired or invalid JWT token");
        }
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
