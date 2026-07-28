package com.spring.deliveryservice.infrastructure.client;


import com.spring.deliveryservice.global.exception.ApiResponse;
import com.spring.deliveryservice.presentation.request.ReserveRequest;
import com.spring.deliveryservice.presentation.response.ReserveResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service")
public interface InventoryClient {
    @PostMapping("/internal/inventory/reserve")
    ApiResponse<ReserveResponse> reserve(@RequestBody ReserveRequest reserveRequest);
}
