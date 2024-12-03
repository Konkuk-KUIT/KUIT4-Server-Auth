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
}