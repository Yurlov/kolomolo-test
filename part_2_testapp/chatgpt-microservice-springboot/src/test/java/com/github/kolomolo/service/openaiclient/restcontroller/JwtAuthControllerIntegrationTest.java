package com.github.kolomolo.service.openaiclient.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kolomolo.service.openaiclient.model.request.AuthJwtRequest;
import com.github.kolomolo.service.openaiclient.service.UserService;
import com.github.kolomolo.service.openaiclient.service.JwtTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Date;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class JwtAuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtTokenService jwtTokenService;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        when(userService.loadUserByUsername("testUser"))
                .thenReturn(org.springframework.security.core.userdetails.User.withUsername("testUser")
                        .password(passwordEncoder.encode("password"))
                        .authorities("ROLE_USER")
                        .build());
        when(jwtTokenService.generateToken("testUser")).thenReturn("testToken");
        when(jwtTokenService.getExpirationTime()).thenReturn(new Date().getTime());
    }

    @Test
    void authenticateUser_ValidCredentials_ReturnsJwtToken() throws Exception {
        AuthJwtRequest authJwtRequest = new AuthJwtRequest("testUser", "password");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authJwtRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.expiresIn").exists());
    }

    @Test
    void authenticateUser_InvalidCredentials_ReturnsUnauthorized() throws Exception {
        AuthJwtRequest authJwtRequest = new AuthJwtRequest("testUser", "wrongPassword");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authJwtRequest)))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
