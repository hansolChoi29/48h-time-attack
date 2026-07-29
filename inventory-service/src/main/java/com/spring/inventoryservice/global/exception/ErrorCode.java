package com.spring.inventoryservice.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INVENTORY_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "INVENTORY_NOT_FOUND",
            "재고를 찾을 수 없음"
    ),
    INVENTORY_ALREADY_EXISTS(
            HttpStatus.CONFLICT,
            "INVENTORY_CONFLICT",
            "재고가 이미 있음"
    ),
    INVENTORY_VALID(
            HttpStatus.BAD_REQUEST,
            "INVENTORY_VALID",
            "차감 수량이 올바르지 않음"
    ),
    ;

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
