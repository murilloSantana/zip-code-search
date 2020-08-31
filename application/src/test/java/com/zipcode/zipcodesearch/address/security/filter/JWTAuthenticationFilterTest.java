package com.zipcode.zipcodesearch.address.security.filter;

import com.zipcode.zipcodesearch.security.model.ApiKeyAuthenticationToken;
import com.zipcode.zipcodesearch.security.service.JWTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class JWTAuthenticationFilterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JWTService jwtService;

    @BeforeEach
    public void setUp() {
        this.jwtService.setSecretKey("2345678");
    }

    @Test
    public void testAuthenticateAuthorizationEmpty() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("authorization", "");

        this.mockMvc.perform(get("/address").headers(httpHeaders))
                .andExpect(status().isForbidden())
                .andReturn();

        verify(jwtService, times(0)).getAuthenticationRequestByToken(any());
    }

    @Test
    public void testAuthenticateAuthorizationNull() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();

        this.mockMvc.perform(get("/address").headers(httpHeaders))
                .andExpect(status().isForbidden())
                .andReturn();

        verify(jwtService, times(0)).getAuthenticationRequestByToken(any());
    }

    @Test
    public void testAuthenticateByAuthorizationHeader() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("authorization", "Bearer 354564535353434343");

        ApiKeyAuthenticationToken apiKeyAuthenticationToken =
                new ApiKeyAuthenticationToken("apiKey", "email", Collections.emptyList());

        when(jwtService.getAuthenticationRequestByToken(any())).thenReturn(apiKeyAuthenticationToken);

        this.mockMvc.perform(get("/address").header("content-type", "application/json")
                .headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();

        verify(jwtService, times(1)).getAuthenticationRequestByToken(any());
    }
}
