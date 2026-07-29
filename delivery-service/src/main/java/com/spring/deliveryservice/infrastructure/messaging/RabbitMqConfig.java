package com.spring.deliveryservice.infrastructure.messaging;

import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String DELIVERY_EXCHANGE = "delivery.exchange";

    public static final String DELIVERY_CREATED_QUEUE = "delivery.created.queue";

    public static final String DELIVERY_CREATED_ROUTING_KEY = "delivery.created";

    @Bean
    public TopicExchange deliveryExchange() {
        return new TopicExchange(DELIVERY_EXCHANGE);
    }

    @Bean
    public Queue deliveryCreatedQueue() {
        return QueueBuilder
                .durable(DELIVERY_CREATED_QUEUE)
                .build();
    }

    @Bean
    public Binding deliveryCreatedBinding(
            Queue deliveryCreatedQueue,
            TopicExchange deliveryExchange
    ) {
        return BindingBuilder
                .bind(deliveryCreatedQueue)
                .to(deliveryExchange)
                .with(DELIVERY_CREATED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
