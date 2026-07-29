package com.spring.deliveryservice.application.service;


import com.spring.deliveryservice.domain.entity.Delivery;
import com.spring.deliveryservice.domain.event.DeliveryCreatedEvent;
import com.spring.deliveryservice.domain.repository.DeliveryRepository;
import com.spring.deliveryservice.application.command.CreateDeliveryCommand;
import com.spring.deliveryservice.application.result.CreateDeliveryResult;
import com.spring.deliveryservice.infrastructure.client.InventoryClient;
import com.spring.deliveryservice.infrastructure.messaging.DeliveryEventPublisher;
import com.spring.deliveryservice.presentation.request.ReleaseRequest;
import com.spring.deliveryservice.presentation.request.ReserveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final InventoryClient inventoryClient;
    private final DeliveryEventPublisher deliveryEventPublisher;

    @Transactional
    public CreateDeliveryResult createDelivery(
            CreateDeliveryCommand createdDeliveryCommand
    ) {
        inventoryClient.reserve(
                new ReserveRequest(
                        createdDeliveryCommand.productId(),
                        createdDeliveryCommand.quantity()
                )
        );

        try {
            Delivery delivery = Delivery.create(
                    createdDeliveryCommand.userId(),
                    createdDeliveryCommand.productId(),
                    createdDeliveryCommand.quantity(),
                    createdDeliveryCommand.address()
            );
//            throw new RuntimeException("보상 트랜잭션 테스트");
            Delivery savedDelivery = deliveryRepository.save(delivery);

            DeliveryCreatedEvent deliveryCreatedEvent = new DeliveryCreatedEvent(
                    savedDelivery.getId(),
                    savedDelivery.getUserId(),
                    savedDelivery.getProductId(),
                    savedDelivery.getQuantity(),
                    savedDelivery.getAddress(),
                    savedDelivery.getStatus()
            );
            deliveryEventPublisher.publish(deliveryCreatedEvent);

            return new CreateDeliveryResult(
                    savedDelivery.getId(),
                    savedDelivery.getStatus()
            );
        } catch (RuntimeException runtimeException) {
            inventoryClient.release(
                    new ReleaseRequest(
                            createdDeliveryCommand.productId(),
                            createdDeliveryCommand.quantity()
                    )
            );

            log.warn(
                    "배송 생성 실패로 재고 복구. productId={}, quantity={}",
                    createdDeliveryCommand.productId(),
                    createdDeliveryCommand.quantity()
            );

            throw runtimeException;
        }
    }
}

// 즉, 인벤토리 디비 재고 차감과 딜리버리 디비 배송 저장으로 구분
// Local transactions are resource-specific. : 로컬 트랜잭션은 특정 자원에 종속된다
// 딜리버리 DB 저장 실패 시 딜리버리 DB 작업은 롤백 가능하지만
// 이미 HTTP 요청으로 완료된 인벤토리 디비 재고 차감은 자동 롤백이 불가능해진다
// Feign은 DB 접근이 아니라 HTTP 통신 - 선언적 웹 서비스 클라이언트