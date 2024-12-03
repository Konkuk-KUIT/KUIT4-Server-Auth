package com.kuit.kuit4serverauth.global.response;

import org.springframework.http.HttpStatus;

public interface ResponseStatus {

    int getStatus();
    String getMessage();
    long getTimestamp();
}
