package com.spring.deliveryservice.presentation.response;

import java.util.UUID;

public record CreateDeliveryResponse(
        UUID productId,
        int remainingQuantity
) {
}
