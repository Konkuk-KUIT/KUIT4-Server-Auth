package com.kuit.kuit4serverauth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Token {
    private final String AccessToken;
    private final String RefreshToken;
}
