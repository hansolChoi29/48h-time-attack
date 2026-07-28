package com.spring.inventoryservice.presentation.request;

import java.util.UUID;

public record CreateInventoryRequest(
        UUID productId,
        String productName,
        int quantity
) {
}
