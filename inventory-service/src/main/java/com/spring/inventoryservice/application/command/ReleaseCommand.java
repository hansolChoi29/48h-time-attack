package com.spring.inventoryservice.application.command;

import java.util.UUID;

public record ReleaseCommand(
        UUID productId,
        int quantity
) {
}
