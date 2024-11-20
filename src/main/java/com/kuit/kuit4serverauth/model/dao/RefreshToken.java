package com.kuit.kuit4serverauth.model.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RefreshToken {
    private Long id;
    private String username;
    private String refreshToken;
    private Date expiresAt;

    public RefreshToken() {
    }

    public RefreshToken(String username, String refreshToken, Date expiresAt) {
        this.username = username;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
    }
}
