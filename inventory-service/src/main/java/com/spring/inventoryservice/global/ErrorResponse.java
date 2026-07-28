package com.spring.inventoryservice.global;

public record ErrorResponse(
        String code,
        String message
) {
}
