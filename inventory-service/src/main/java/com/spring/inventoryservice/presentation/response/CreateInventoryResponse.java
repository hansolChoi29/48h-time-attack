package com.spring.inventoryservice.presentation.response;

import java.util.UUID;

public record CreateInventoryResponse(
        UUID inventoryId,
        UUID productId
) {
}
