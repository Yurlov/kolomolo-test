package com.github.kolomolo.service.openaiclient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password";
    private static final String USER_ROLE = "USER";

    public UserDetails loadUserByUsername(String username) {
        if (USERNAME.equals(username)) {
            return User.builder()
                            .username(USERNAME)
                            .password(passwordEncoder.encode(PASSWORD))
                            .roles(USER_ROLE)
                            .build();
        }
        return null;
    }
}

