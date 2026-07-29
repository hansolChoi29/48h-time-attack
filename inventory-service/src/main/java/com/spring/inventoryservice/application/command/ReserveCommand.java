package com.spring.inventoryservice.application.command;

import java.util.UUID;

public record ReserveCommand(
        UUID productId,
        int quantity
) {
}
