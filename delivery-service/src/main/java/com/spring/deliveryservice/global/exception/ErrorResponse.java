package com.spring.deliveryservice.global.exception;

public record ErrorResponse(
        String code,
        String message
) {
}
