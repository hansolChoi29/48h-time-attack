package com.spring.deliveryservice.presentation.response;

import java.util.UUID;

public record ReserveResponse(
        UUID productId,
        int remainingQuantity
) {
}
