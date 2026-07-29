package com.spring.notificationservice.infrastructure.messaging;


import com.spring.notificationservice.domain.event.DeliveryCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeliveryCreatedEventListener {
    // RabbitListener : elivery.created.queue에 메시지가 들어오면 handle() 메서드를 자동 실행한다, @RabbitListener가 붙은 메서드마다 뒤에서 메시지 리스너 컨테이너가 생성된다고 설명한다
    @RabbitListener(queues = "delivery.created.queue")
    public void handle(DeliveryCreatedEvent deliveryCreatedEvent) {
        log.info(
                "[배송 생성 알림] deliveryId={}, userId={}, productId={}, quantity={}, status={}",
                deliveryCreatedEvent.deliveryId(),
                deliveryCreatedEvent.userId(),
                deliveryCreatedEvent.productId(),
                deliveryCreatedEvent.quantity(),
                deliveryCreatedEvent.status()
        );
    }
}
/*
 * 재고예약 성공
 * 배송 생성 및 저장
 * 배송 생성 이벤트 발행
 * Delivery service는 응답 반환
 * Notification Service는 별도로 이벤트를 받아 로그 출력
 */