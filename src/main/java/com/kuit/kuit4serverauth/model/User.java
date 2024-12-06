package com.kuit.kuit4serverauth.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private Long userId;
    private String username;
    private String password;
    private String role; // e.g., ROLE_USER, ROLE_ADMIN
}