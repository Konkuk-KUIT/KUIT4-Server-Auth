package com.kuit.kuit4serverauth.domain.user.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RefreshRequest {
    private String refreshToken;

    @Builder
    public RefreshRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
