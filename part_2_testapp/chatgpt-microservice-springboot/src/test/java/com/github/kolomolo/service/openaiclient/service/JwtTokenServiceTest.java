package com.github.kolomolo.service.openaiclient.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtTokenServiceTest {

    @InjectMocks
    private JwtTokenService jwtTokenService;

    private final String username = "testUser";
    private final String secret = "e344354354dewdwewd3r34r34r43r43d43d";
    private final long expiration = 3600;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(jwtTokenService, "secret", secret);
        ReflectionTestUtils.setField(jwtTokenService, "expiration", expiration);
    }

    @Test
    void generateToken_ValidUsername_ReturnsToken() {
        String token = jwtTokenService.generateToken(username);
        assertNotNull(token);
    }

    @Test
    void generateToken_NullUsername_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> jwtTokenService.generateToken(null));
    }

    @Test
    void getUsernameFromToken_ValidToken_ReturnsUsername() {
        String token = jwtTokenService.generateToken(username);
        String retrievedUsername = jwtTokenService.getUsernameFromToken(token);
        assertEquals(username, retrievedUsername);
    }

    @Test
    void validateToken_ValidToken_ReturnsTrue() {
        String token = jwtTokenService.generateToken(username);
        assertTrue(jwtTokenService.validateToken(token));
    }

    @Test
    void validateToken_ExpiredToken_ReturnsFalse() {
        String expiredToken = generateExpiredToken();
        assertFalse(jwtTokenService.validateToken(expiredToken));
    }

    @Test
    void validateToken_InvalidToken_ReturnsFalse() {
        String invalidToken = "invalidToken";
        assertFalse(jwtTokenService.validateToken(invalidToken));
    }

    private String generateExpiredToken() {
        Date pastDate = Date.from(Instant.now().minusSeconds(3600));
        return io.jsonwebtoken.Jwts.builder()
                .setSubject(username)
                .setIssuedAt(pastDate)
                .setExpiration(pastDate)
                .signWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
}