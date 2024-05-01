package com.github.kolomolo.service.openaiclient.restcontroller;

import com.github.kolomolo.service.openaiclient.model.request.AuthJwtRequest;
import com.github.kolomolo.service.openaiclient.model.response.AuthJwtResponse;
import com.github.kolomolo.service.openaiclient.service.UserService;
import com.github.kolomolo.service.openaiclient.service.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class JwtAuthController {

    private final JwtTokenService jwtTokenService;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/auth")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthJwtRequest authJwtRequest) {
        UserDetails userDetails = userService.loadUserByUsername(authJwtRequest.getUsername());
        if (userDetails != null && passwordEncoder.matches(authJwtRequest.getPassword(), userDetails.getPassword())) {
            String token = jwtTokenService.generateToken(authJwtRequest.getUsername());

            return ResponseEntity.ok(new AuthJwtResponse(token, jwtTokenService.getExpirationTime()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
