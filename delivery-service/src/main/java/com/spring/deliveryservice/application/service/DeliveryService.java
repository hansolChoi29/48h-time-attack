package com.spring.deliveryservice.application.service;


import com.spring.deliveryservice.domain.entity.Delivery;
import com.spring.deliveryservice.domain.event.DeliveryCreatedEvent;
import com.spring.deliveryservice.domain.repository.DeliveryRepository;
import com.spring.deliveryservice.application.command.CreateDeliveryCommand;
import com.spring.deliveryservice.application.result.CreateDeliveryResult;
import com.spring.deliveryservice.infrastructure.client.InventoryClient;
import com.spring.deliveryservice.infrastructure.messaging.DeliveryEventPublisher;
import com.spring.deliveryservice.presentation.request.ReserveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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
        Delivery delivery = Delivery.create(
                createdDeliveryCommand.userId(),
                createdDeliveryCommand.productId(),
                createdDeliveryCommand.quantity(),
                createdDeliveryCommand.address()
        );

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
    }
}
