package com.spring.deliveryservice.domain.repository;

import com.spring.deliveryservice.domain.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {
}
