package com.spring.deliveryservice.infrastructure.messaging;

import com.spring.deliveryservice.domain.event.DeliveryCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    // Queue가 필요하고 Exchange가 필요하고
    // Queue와 Exchange를 Routing key로 연결해야 함
    public void publish(
            DeliveryCreatedEvent deliveryCreatedEvent
    ) {
        rabbitTemplate.convertAndSend(
                RabbitMqConfig.DELIVERY_EXCHANGE,
                RabbitMqConfig.DELIVERY_CREATED_ROUTING_KEY,
                deliveryCreatedEvent
        );
    }
}
