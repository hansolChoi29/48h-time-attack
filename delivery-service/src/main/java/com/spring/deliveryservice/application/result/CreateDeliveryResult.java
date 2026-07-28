package com.spring.deliveryservice.application.result;

import java.util.UUID;

public record CreateDeliveryResult(
        UUID productId,
        int remainingQuantity
) {
}
