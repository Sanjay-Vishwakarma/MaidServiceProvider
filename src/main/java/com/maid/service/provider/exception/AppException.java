package com.maid.service.provider.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

    private final int statusCode;
    private final String errorType;

    public AppException(String message, int statusCode, String errorType) {
        super(message);
        this.statusCode = statusCode;
        this.errorType = errorType;
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = 500;
        this.errorType = "INTERNAL_ERROR";
    }

    public AppException(String message, String errorDetail) {
        super(message + ": " + errorDetail);
        this.statusCode = 500;
        this.errorType = "INTERNAL_ERROR";
    }
}
