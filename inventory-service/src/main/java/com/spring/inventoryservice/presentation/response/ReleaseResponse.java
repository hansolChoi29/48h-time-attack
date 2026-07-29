package com.spring.inventoryservice.presentation.response;

import java.util.UUID;

public record ReleaseResponse(
        UUID productId,
        int quantity
) {
}
