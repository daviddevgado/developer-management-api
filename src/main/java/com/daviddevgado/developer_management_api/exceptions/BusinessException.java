package com.daviddevgado.developer_management_api.exceptions;

import org.springframework.http.HttpStatus;

public abstract class BusinessException extends RuntimeException {
    private final String errorCode;
    private final HttpStatus httpStatus;

    public BusinessException(String message, String errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public BusinessException(String message) {
        this(message, "BUSINESS_ERROR", HttpStatus.BAD_REQUEST);
    }

    public String getErrorCode() {return errorCode;}
    public HttpStatus getHttpStatus() {return httpStatus;}
}
