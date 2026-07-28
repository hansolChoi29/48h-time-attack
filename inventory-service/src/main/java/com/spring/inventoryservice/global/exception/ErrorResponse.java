package com.spring.inventoryservice.global.exception;

public record ErrorResponse(
        String code,
        String message
) {
}
