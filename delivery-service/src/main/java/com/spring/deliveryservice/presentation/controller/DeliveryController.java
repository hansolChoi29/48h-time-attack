package com.spring.deliveryservice.presentation.controller;


import com.spring.deliveryservice.application.service.DeliveryService;
import com.spring.deliveryservice.global.exception.ApiResponse;
import com.spring.deliveryservice.application.command.CreateDeliveryCommand;
import com.spring.deliveryservice.presentation.request.CreateDeliveryRequest;
import com.spring.deliveryservice.presentation.response.CreateDeliveryResponse;
import com.spring.deliveryservice.application.result.CreateDeliveryResult;
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
public class DeliveryController {
    private final DeliveryService deliveryService;


    @PostMapping("/reserve")
    public ResponseEntity<ApiResponse<CreateDeliveryResponse>> createDelivery(
            @RequestBody CreateDeliveryRequest createDeliveryRequest
    ) {
        CreateDeliveryCommand createdDeliveryCommand = new CreateDeliveryCommand(
                createDeliveryRequest.productId(),
                createDeliveryRequest.quantity()
        );

        CreateDeliveryResult createdDeliveryResult = deliveryService.createDelivery(createdDeliveryCommand);

        CreateDeliveryResponse createdDeliveryResponse = new CreateDeliveryResponse(
                createdDeliveryResult.productId(),
                createdDeliveryResult.remainingQuantity()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        HttpStatus.CREATED,
                        "생성 성공!",
                        createdDeliveryResponse
                ));
    }
}
