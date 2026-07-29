package com.spring.deliveryservice.application.result;

import com.spring.deliveryservice.domain.enums.DeliveryStatus;

import java.util.UUID;

public record CreateDeliveryResult(
        UUID deliveryId,
        DeliveryStatus status
) {
}
