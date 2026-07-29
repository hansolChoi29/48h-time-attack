package com.spring.deliveryservice.application.command;

import java.util.UUID;

public record CreateDeliveryCommand(
        UUID userId,
        UUID productId,
        int quantity,
        String address
) {
}
