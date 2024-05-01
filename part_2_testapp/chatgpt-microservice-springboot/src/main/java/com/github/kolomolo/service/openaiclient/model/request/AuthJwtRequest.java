package com.github.kolomolo.service.openaiclient.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthJwtRequest {
    private String username;
    private String password;
}
