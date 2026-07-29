package com.spring.inventoryservice.global.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@MappedSuperclass // 클래스를 테이블로 만들지는 않지만, 필드를 자식 엔티티에 상속시키겠다
@EntityListeners(AuditingEntityListener.class) // 엔티티 생성·수정 시점을 감지해서 @CreatedDate, @LastModifiedDate 값을 자동으로 넣어주는 장치
public abstract class BaseEntity {
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;
}
