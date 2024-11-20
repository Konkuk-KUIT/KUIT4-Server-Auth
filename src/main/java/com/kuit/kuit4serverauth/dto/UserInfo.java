package com.kuit.kuit4serverauth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class UserInfo {

    private final String username;
    private final String role;
}
