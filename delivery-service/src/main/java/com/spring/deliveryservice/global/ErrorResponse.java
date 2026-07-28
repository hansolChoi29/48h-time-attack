package com.spring.deliveryservice.global;

public record ErrorResponse(
        String code,
        String message
) {
}
