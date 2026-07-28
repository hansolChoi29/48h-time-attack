package com.spring.inventoryservice.presentation.controller;


import com.spring.inventoryservice.application.service.InventoryService;
import com.spring.inventoryservice.global.exception.ApiResponse;
import com.spring.inventoryservice.application.command.CreateInventoryCommand;
import com.spring.inventoryservice.presentation.request.CreateInventoryRequest;
import com.spring.inventoryservice.presentation.response.CreateInventoryResponse;
import com.spring.inventoryservice.application.result.CreateInventoryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventories")
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CreateInventoryResponse>> createInventory(
            @RequestBody CreateInventoryRequest createInventoryRequest
    ) {
        CreateInventoryCommand createdInventoryCommand = new CreateInventoryCommand(
                createInventoryRequest.productId(),
                createInventoryRequest.productName(),
                createInventoryRequest.quantity()
        );

        CreateInventoryResult createdInventoryResult = inventoryService.createInventory(createdInventoryCommand);

        CreateInventoryResponse createdInventoryResponse = new CreateInventoryResponse(
                createdInventoryResult.inventoryId(),
                createdInventoryResult.productId()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        HttpStatus.CREATED,
                        "생성 성공!",
                        createdInventoryResponse
                ));
    }
}
