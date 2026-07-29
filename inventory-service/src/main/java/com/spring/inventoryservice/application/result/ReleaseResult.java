package com.spring.inventoryservice.application.result;

import java.util.UUID;

public record ReleaseResult(
        UUID productId,
        int quantity
) {
}
