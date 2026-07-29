package com.spring.inventoryservice.presentation.request;

import java.util.UUID;

public record ReleaseRequest(
        UUID productId,
        int quantity
) {
}
