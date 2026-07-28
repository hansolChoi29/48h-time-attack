package com.spring.deliveryservice.domain.event;

import java.util.UUID;

public record DeliveryCreatedEvent(
        UUID deliveryId,
        UUID userId,
        UUID productId,
        int quantity,
        String address,
        String status
) {
}
