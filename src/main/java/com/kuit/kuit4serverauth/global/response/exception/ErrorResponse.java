package com.kuit.kuit4serverauth.global.response.exception;

import com.kuit.kuit4serverauth.global.response.ResponseStatus;

public class ErrorResponse implements ResponseStatus {
    private final int status;
    private final String message;
    private final long timestamp;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }
}
