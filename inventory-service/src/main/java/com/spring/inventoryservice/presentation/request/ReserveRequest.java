package com.spring.inventoryservice.presentation.request;

import java.util.UUID;

public record ReserveRequest(
        UUID productId,
        int quantity
) {
}
