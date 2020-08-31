package com.zipcode.zipcodesearch.security.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zipcode.zipcodesearch.security.model.ApiKeyAuthenticationToken;
import com.zipcode.zipcodesearch.security.service.JWTService;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

public class ApiKeyFilter extends AbstractAuthenticationProcessingFilter {

    private String apiKey;
    private String email;
    private JWTService jwtService;

    public ApiKeyFilter(HttpMethod httpMethod, String url, AuthenticationManager authManager, JWTService jwtService) {
        super(new AntPathRequestMatcher(url, httpMethod.name()));
        setAuthenticationManager(authManager);
        this.jwtService = jwtService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        this.apiKey = request.getHeader("x-api-key");
        this.email = request.getHeader("email");

        return getAuthenticationManager().authenticate(new ApiKeyAuthenticationToken(this.apiKey, this.email, Collections.emptyList()));
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain,
            Authentication auth) {

        String authorizationToken = jwtService
                .generateToken(new ApiKeyAuthenticationToken(this.apiKey, this.email, Collections.emptyList()));

        response.addHeader("authorization", authorizationToken);
    }
}
