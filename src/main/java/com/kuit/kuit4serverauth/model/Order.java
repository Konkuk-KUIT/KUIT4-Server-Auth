package com.kuit.kuit4serverauth.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Order {

    private Long orderId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int totalPrice;
    private Long userId;
    private Long menuId;
}
