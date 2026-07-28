package com.spring.deliveryservice.application.service;


import com.spring.deliveryservice.domain.repository.DeliveryRepository;
import com.spring.deliveryservice.application.command.CreateDeliveryCommand;
import com.spring.deliveryservice.application.result.CreateDeliveryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    @Transactional
    public CreateDeliveryResult createDelivery(
            CreateDeliveryCommand createdDeliveryCommand
    ) {
        return null;
    }

}
