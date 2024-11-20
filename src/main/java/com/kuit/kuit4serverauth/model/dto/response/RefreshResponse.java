package com.kuit.kuit4serverauth.model.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RefreshResponse {
    private final String accessToken;

    public static RefreshResponse of(String accessToken) {
        return new RefreshResponse(accessToken);
    }
}
