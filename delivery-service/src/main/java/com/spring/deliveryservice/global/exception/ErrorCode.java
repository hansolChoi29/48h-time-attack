package com.spring.deliveryservice.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INVENTORY_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "INVENTORY_NOT_FOUND",
            "재고를 찾을 수 없습니다."
    );
    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(
            HttpStatus status,
            String code,
            String message
    ) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
