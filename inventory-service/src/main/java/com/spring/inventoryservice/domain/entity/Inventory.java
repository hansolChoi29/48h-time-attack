package com.spring.inventoryservice.domain.entity;

import com.spring.inventoryservice.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Table(name = "inventory")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inventory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_id", nullable = false, unique = true)
    private UUID productId;

    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public static Inventory create(
            UUID productId,
            String productName,
            int quantity
    ) {
        Inventory inventory = new Inventory();
        inventory.productId = productId;
        inventory.productName = productName;
        inventory.quantity = quantity;
        return inventory;
    }

    public void reserve(int quantity) {
        this.quantity -= quantity;
    }
}
