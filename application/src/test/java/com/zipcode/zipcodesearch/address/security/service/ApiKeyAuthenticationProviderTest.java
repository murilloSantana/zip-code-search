package com.zipcode.zipcodesearch.address.security.service;

import com.zipcode.zipcodesearch.address.controller.dto.AddressDTO;
import com.zipcode.zipcodesearch.security.model.ApiKeyAuthenticationToken;
import com.zipcode.zipcodesearch.security.service.ApiKeyAuthenticationProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ApiKeyAuthenticationProviderTest {

    @InjectMocks
    public ApiKeyAuthenticationProvider apiKeyAuthenticationProvider;

    private String apiKey = "2345678";

    @BeforeEach
    public void setUp() {
        apiKeyAuthenticationProvider.setServerApiKey(this.apiKey);
    }

    @Test
    public void testCheckSupportsValidClass() {
        assertTrue(apiKeyAuthenticationProvider.supports(ApiKeyAuthenticationToken.class));
    }

    @Test
    public void testCheckSupportsInValidClass() {
        assertFalse(apiKeyAuthenticationProvider.supports(AddressDTO.class));
    }

    @Test
    public void testInvalidAuthenticateWhenPrincipalIsNull() {
        ApiKeyAuthenticationToken apiKeyAuthenticationToken =
                new ApiKeyAuthenticationToken(null, "mock@gmail.com", Collections.emptyList());

        assertNull(apiKeyAuthenticationProvider.authenticate(apiKeyAuthenticationToken));
    }

    @Test
    public void testInvalidAuthenticateWhenCredentialsIsNull() {
        ApiKeyAuthenticationToken apiKeyAuthenticationToken =
                new ApiKeyAuthenticationToken("323232", null, Collections.emptyList());

        assertNull(apiKeyAuthenticationProvider.authenticate(apiKeyAuthenticationToken));
    }

    @Test
    public void testInvalidAuthenticateWhenCredentialsAndPrincialAreNull() {
        ApiKeyAuthenticationToken apiKeyAuthenticationToken =
                new ApiKeyAuthenticationToken(null, null, Collections.emptyList());

        assertNull(apiKeyAuthenticationProvider.authenticate(apiKeyAuthenticationToken));
    }

    @Test
    public void testInvalidAuthenticateWhenPrincipalIsWrong() {
        ApiKeyAuthenticationToken apiKeyAuthenticationToken =
                new ApiKeyAuthenticationToken("323232", "mock@gmail.com", Collections.emptyList());

        assertNull(apiKeyAuthenticationProvider.authenticate(apiKeyAuthenticationToken));
    }

    @Test
    public void testValidAuthenticate() {
        ApiKeyAuthenticationToken apiKeyAuthenticationToken =
                new ApiKeyAuthenticationToken(apiKey, "mock@gmail.com", Collections.emptyList());

        assertNotNull(apiKeyAuthenticationProvider.authenticate(apiKeyAuthenticationToken));
    }
}
