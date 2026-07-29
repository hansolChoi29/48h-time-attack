package com.spring.deliveryservice.presentation.request;

import java.util.UUID;

public record CreateDeliveryRequest(
        UUID userId,
        UUID productId,
        int quantity,
        String address
) {
}
