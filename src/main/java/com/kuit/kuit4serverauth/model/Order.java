package com.kuit.kuit4serverauth.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private long orderId;
    private long userId;
    private long storeId;
    private long price;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
}
