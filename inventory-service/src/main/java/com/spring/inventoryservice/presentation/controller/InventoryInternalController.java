package com.spring.inventoryservice.presentation.controller;


import com.spring.inventoryservice.application.command.ReleaseCommand;
import com.spring.inventoryservice.application.command.ReserveCommand;
import com.spring.inventoryservice.application.result.ReleaseResult;
import com.spring.inventoryservice.application.result.ReserveResult;
import com.spring.inventoryservice.application.service.InventoryService;
import com.spring.inventoryservice.global.exception.ApiResponse;
import com.spring.inventoryservice.presentation.request.ReleaseRequest;
import com.spring.inventoryservice.presentation.request.ReserveRequest;
import com.spring.inventoryservice.presentation.response.ReleaseResponse;
import com.spring.inventoryservice.presentation.response.ReserveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/inventories")
public class InventoryInternalController {
    private final InventoryService inventoryService;

    @PostMapping("/reserve")
    public ResponseEntity<ApiResponse<ReserveResponse>> reserve(
            @RequestBody ReserveRequest reserveRequest
    ) {
        ReserveCommand reserveCommand = new ReserveCommand(
                reserveRequest.productId(),
                reserveRequest.quantity()
        );

        ReserveResult reserveResult = inventoryService.reserve(reserveCommand);

        ReserveResponse reserveResponse = new ReserveResponse(
                reserveResult.productId(),
                reserveResult.remainingQuantity()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(
                        HttpStatus.OK,
                        "재고 예약 성공",
                        reserveResponse
                ));
    }

    @PostMapping("/release")
    public ResponseEntity<ApiResponse<ReleaseResponse>> release(
            @RequestBody ReleaseRequest releaseRequest
    ) {
        ReleaseCommand releaseCommand = new ReleaseCommand(
                releaseRequest.productId(),
                releaseRequest.quantity()
        );

        ReleaseResult releaseResult = inventoryService.release(releaseCommand);

        ReleaseResponse releaseResponse = new ReleaseResponse(
                releaseResult.productId(),
                releaseResult.quantity()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(
                        HttpStatus.OK,
                        "재고 복구 성공",
                        releaseResponse
                ));
    }
}
