package com.kuit.kuit4serverauth.domain.user.model.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenResponse {

    private final String accessToken;
    private final String refreshToken;

    public static TokenResponse of(String accessToken, String refreshToken) {
        return new TokenResponse(accessToken, refreshToken);
    }
}
