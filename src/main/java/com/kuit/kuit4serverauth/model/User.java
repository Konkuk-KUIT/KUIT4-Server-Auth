package com.kuit.kuit4serverauth.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class User {
    private Long id;
    private String role;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String nickname;
    private String grade;
    private String status;
    private Timestamp created_at;
    private Timestamp updated_at;
}