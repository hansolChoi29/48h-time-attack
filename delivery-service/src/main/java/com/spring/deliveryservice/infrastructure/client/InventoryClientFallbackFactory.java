package com.spring.deliveryservice.infrastructure.client;


import com.spring.deliveryservice.global.exception.BusinessException;
import com.spring.deliveryservice.global.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryClientFallbackFactory implements FallbackFactory<InventoryClient> {

    @Override
    public InventoryClient create(Throwable cause) {
        return reserveRequest -> {
            log.error(
                    "Inventory service 호출 실패. productId={}, quantity={}",
                    reserveRequest.productId(),
                    reserveRequest.quantity(),
                    cause
            );

            throw new BusinessException(
                    ErrorCode.INVENTORY_SERVICE_UNAVAILABLE
            );
        };
    }
}
