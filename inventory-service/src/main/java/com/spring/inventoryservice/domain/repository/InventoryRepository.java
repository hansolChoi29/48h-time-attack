package com.spring.inventoryservice.domain.repository;

import com.spring.inventoryservice.domain.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
    boolean existsByProductId(UUID productId);

    Optional<Inventory> findByProductId(UUID productId);
}
