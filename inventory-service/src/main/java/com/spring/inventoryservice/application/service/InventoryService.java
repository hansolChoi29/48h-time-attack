package com.spring.inventoryservice.application.service;


import com.spring.inventoryservice.domain.entity.Inventory;
import com.spring.inventoryservice.domain.repository.InventoryRepository;
import com.spring.inventoryservice.application.command.CreateInventoryCommand;
import com.spring.inventoryservice.application.result.CreateInventoryResult;
import com.spring.inventoryservice.global.exception.BusinessException;
import com.spring.inventoryservice.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional
    public CreateInventoryResult createInventory(CreateInventoryCommand createdInventoryCommand) {
        if (inventoryRepository.existsByProductId(createdInventoryCommand.productId())) {
            throw new BusinessException(ErrorCode.INVENTORY_ALREADY_EXISTS);
        }

        Inventory inventory = Inventory.create(
                createdInventoryCommand.productId(),
                createdInventoryCommand.productName(),
                createdInventoryCommand.quantity()
        );
        Inventory saveInventory = inventoryRepository.save(inventory);

        return new CreateInventoryResult(
                saveInventory.getId(),
                saveInventory.getProductId()
        );
    }
}
