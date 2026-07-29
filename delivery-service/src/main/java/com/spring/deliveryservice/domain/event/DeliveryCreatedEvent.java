package com.spring.deliveryservice.domain.event;

import com.spring.deliveryservice.domain.enums.DeliveryStatus;

import java.util.UUID;

public record DeliveryCreatedEvent(
        UUID deliveryId,
        UUID userId,
        UUID productId,
        int quantity,
        String address,
        DeliveryStatus status
) {
}
