package com.zipcode.zipcodesearch.security.service;

import com.zipcode.zipcodesearch.security.model.ApiKeyAuthenticationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

    @Value("${server.authentication.api.key}")
    private String serverApiKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if(Objects.isNull(authentication.getPrincipal()) || Objects.isNull(authentication.getCredentials())) {
            return null;
        }

        if(!authentication.getPrincipal().equals(serverApiKey)) return null;

        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(ApiKeyAuthenticationToken.class);
    }
}
