package com.zipcode.zipcodesearch.address.security.filter;

import com.zipcode.zipcodesearch.security.service.JWTService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ApiKeyFilterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JWTService jwtService;

    @Test
    public void testAuthenticateApiKeyEmpty() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("x-api-key", "");
        httpHeaders.add("email", "mock.user@gmail.com");

        this.mockMvc.perform(post("/authenticate").headers(httpHeaders))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    public void testAuthenticateApiKeyNull() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("email", "mock.user@gmail.com");

        this.mockMvc.perform(post("/authenticate").headers(httpHeaders))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    public void testAuthenticateEmailEmpty() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("x-api-key", "212121");
        httpHeaders.add("email", "");

        this.mockMvc.perform(post("/authenticate").headers(httpHeaders))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    public void testAuthenticateEmailNull() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("x-api-key", "212121");

        this.mockMvc.perform(post("/authenticate").headers(httpHeaders))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    public void testGenerateToken() throws Exception {
        String expectedAuthorizationToken = "23554546556575757575";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("x-api-key", "2345678");
        httpHeaders.add("email", "mock.user@gmail.com");

        when(jwtService.generateToken(any())).thenReturn(expectedAuthorizationToken);

        MvcResult authenticateResponse = this.mockMvc.perform(post("/authenticate").headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();

        verify(jwtService, times(1)).generateToken(any());

        Assertions.assertEquals(expectedAuthorizationToken,
                authenticateResponse.getResponse().getHeader("authorization"));
    }
}
