package com.spring.notificationservice.infrastructure.messaging;

import org.springframework.amqp.support.converter.MessageConverter;
import com.spring.notificationservice.domain.event.DeliveryCreatedEvent;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RabbitMqConfig {

    @Bean
    public MessageConverter messageConverter() {
        JacksonJsonMessageConverter converter =
                new JacksonJsonMessageConverter();

        DefaultClassMapper classMapper = new DefaultClassMapper();

        classMapper.setIdClassMapping(Map.of(
                "com.spring.deliveryservice.domain.event.DeliveryCreatedEvent",
                DeliveryCreatedEvent.class
        ));

        converter.setClassMapper(classMapper);

        return converter;
    }
}
