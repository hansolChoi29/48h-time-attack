package com.spring.deliveryservice.presentation.request;

import java.util.UUID;

public record CreateDeliveryRequest(
    UUID productId,
    int quantity
) {
}
