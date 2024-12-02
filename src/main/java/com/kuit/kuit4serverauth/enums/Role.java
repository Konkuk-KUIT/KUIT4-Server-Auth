package com.kuit.kuit4serverauth.enums;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String role;

    Role(String role) {
        this.role = role;
    }
}

