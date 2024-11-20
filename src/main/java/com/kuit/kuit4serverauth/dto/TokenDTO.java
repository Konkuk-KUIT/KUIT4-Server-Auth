package com.kuit.kuit4serverauth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenDTO {

    private final String refreshToken;
    private final String accessToken;
}
