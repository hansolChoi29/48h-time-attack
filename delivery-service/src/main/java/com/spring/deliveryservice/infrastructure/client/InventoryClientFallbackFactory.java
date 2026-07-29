package com.spring.deliveryservice.infrastructure.client;


import com.spring.deliveryservice.global.exception.ApiResponse;
import com.spring.deliveryservice.global.exception.BusinessException;
import com.spring.deliveryservice.global.exception.ErrorCode;
import com.spring.deliveryservice.presentation.request.ReleaseRequest;
import com.spring.deliveryservice.presentation.request.ReserveRequest;
import com.spring.deliveryservice.presentation.response.ReleaseResponse;
import com.spring.deliveryservice.presentation.response.ReserveResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryClientFallbackFactory implements FallbackFactory<InventoryClient> {

    @Override
    public InventoryClient create(Throwable cause) {

        return new InventoryClient() {
            @Override
            public ApiResponse<ReserveResponse> reserve(ReserveRequest reserveRequest) {
                log.error(
                        "재고 차감 호출 실패. productId={}, quantity={}",
                        reserveRequest.productId(),
                        reserveRequest.quantity(),
                        cause
                );

                throw new BusinessException(
                        ErrorCode.INVENTORY_SERVICE_UNAVAILABLE
                );
            }

            @Override
            public ApiResponse<ReleaseResponse> release(ReleaseRequest releaseRequest) {
                log.error(
                        "재고 복구 호출 실패. productId={}, quantity={}",
                        releaseRequest.productId(),
                        releaseRequest.quantity(),
                        cause
                );

                throw new BusinessException(
                        ErrorCode.INVENTORY_SERVICE_UNAVAILABLE
                );
            }
        };
    }
}
