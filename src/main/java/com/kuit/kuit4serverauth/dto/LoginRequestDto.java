package com.kuit.kuit4serverauth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    String username;
    String password;
}
