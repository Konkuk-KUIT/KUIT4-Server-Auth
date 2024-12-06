package com.kuit.kuit4serverauth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshTokenResponseDto {

    private String RefreshToken;

}
