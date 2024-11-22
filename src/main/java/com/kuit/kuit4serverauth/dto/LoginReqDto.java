package com.kuit.kuit4serverauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginReqDto {

    private String username;
    private String password;
}
