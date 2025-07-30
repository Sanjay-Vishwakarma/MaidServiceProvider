package com.maid.service.provider.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String token;
    private String role;

    // Constructor, getters, and setters
    public AuthResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

}