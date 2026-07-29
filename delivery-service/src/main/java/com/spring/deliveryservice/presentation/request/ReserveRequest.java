package com.spring.deliveryservice.presentation.request;

import java.util.UUID;

public record ReserveRequest(
        UUID productId,
        int quantity
) {
}
