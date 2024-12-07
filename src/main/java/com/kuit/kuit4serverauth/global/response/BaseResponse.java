package com.kuit.kuit4serverauth.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonPropertyOrder({"code", "status", "message", "data"})
public class BaseResponse<T> implements ResponseStatus{

    private final int status;
    private final String message;
    private final long timestamp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    public BaseResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.data = data;
    }

    public static <T> BaseResponse<T> of(int status, String message, T data) {
        return new BaseResponse<>(status, message, data);
    }

    public static <T> BaseResponse<T> of(int status, T data) {
        return of(status, "OK", data);
    }

    public static <T> BaseResponse<T> ok(T data) {
        return of(HttpStatus.OK.value(), data);
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
