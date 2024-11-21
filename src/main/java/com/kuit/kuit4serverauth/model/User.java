package com.kuit.kuit4serverauth.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String role; // e.g., ROLE_USER, ROLE_ADMIN

    private User(String username, String role){
        this.username = username;
        this.role = role;
    }

    public static User of(String username, String role){
        return new User(username, role);
    }
}