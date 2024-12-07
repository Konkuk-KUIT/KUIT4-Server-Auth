package com.kuit.kuit4serverauth.domain.user.model.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserInfoRequest {
    private final String username;
    private final String role;
}
