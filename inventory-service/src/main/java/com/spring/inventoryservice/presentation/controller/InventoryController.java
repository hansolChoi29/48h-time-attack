package com.spring.inventoryservice.presentation.controller;


import com.spring.inventoryservice.application.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
}
