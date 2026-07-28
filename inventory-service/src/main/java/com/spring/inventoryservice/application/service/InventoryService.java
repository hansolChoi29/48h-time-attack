package com.spring.inventoryservice.application.service;


import com.spring.inventoryservice.domain.repository.InventoryRepository;
import com.spring.inventoryservice.application.command.CreateInventoryCommand;
import com.spring.inventoryservice.application.result.CreateInventoryResult;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional
    public CreateInventoryResult createInventory(CreateInventoryCommand createdInventoryCommand) {
        return null;
    }
}
