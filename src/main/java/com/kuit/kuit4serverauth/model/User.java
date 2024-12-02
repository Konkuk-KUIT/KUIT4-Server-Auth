package com.kuit.kuit4serverauth.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private long userId;
    private String username;
    private String password;
    private String role; // e.g., ROLE_USER, ROLE_ADMIN

    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;

    private User(String username, String role){
        this.username = username;
        this.role = role;
    }

    public static User of(String username, String role){
        return new User(username, role);
    }
}