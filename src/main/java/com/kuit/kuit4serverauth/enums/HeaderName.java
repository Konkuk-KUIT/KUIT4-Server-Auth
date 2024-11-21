package com.kuit.kuit4serverauth.enums;

import lombok.Getter;

@Getter
public enum HeaderName {
    AUTHORIZATION("Authorization");

    private final String header;

    HeaderName(String header) {
        this.header = header;
    }
}
