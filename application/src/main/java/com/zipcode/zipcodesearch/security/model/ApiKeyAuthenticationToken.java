package com.zipcode.zipcodesearch.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ApiKeyAuthenticationToken extends AbstractAuthenticationToken {

    private Object apiKey;
    private Object email;

    public ApiKeyAuthenticationToken(Object apiKey, Object email,
                                     Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.apiKey = apiKey;
        this.email = email;
        this.setAuthenticated(true);
    }

    @Override
    public Object getPrincipal() {
        return this.apiKey;
    }

    @Override
    public Object getCredentials() {
        return this.email;
    }

}
