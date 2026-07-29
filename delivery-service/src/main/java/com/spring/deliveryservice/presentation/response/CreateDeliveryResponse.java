package com.spring.deliveryservice.presentation.response;

import com.spring.deliveryservice.domain.enums.DeliveryStatus;

import java.util.UUID;

public record CreateDeliveryResponse(
        UUID deliveryId,
        DeliveryStatus status
) {
}
