package com.spring.inventoryservice.presentation.response;

import java.util.UUID;

public record ReserveResponse(
        UUID productId,
        int remainingQuantity
) {
}
