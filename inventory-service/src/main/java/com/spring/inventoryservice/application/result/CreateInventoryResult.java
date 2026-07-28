package com.spring.inventoryservice.application.result;

import java.util.UUID;

public record CreateInventoryResult(
        UUID inventoryId,
        UUID productId
) {
}
