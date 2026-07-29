package com.spring.inventoryservice.application.result;

import java.util.UUID;

public record ReserveResult(
        UUID productId,
        int remainingQuantity
) {
}
