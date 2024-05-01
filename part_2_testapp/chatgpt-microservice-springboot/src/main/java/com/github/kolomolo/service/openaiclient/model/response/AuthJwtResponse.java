package com.github.kolomolo.service.openaiclient.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthJwtResponse {
    private String token;
    private Long expiresIn;
}
