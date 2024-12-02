package com.kuit.kuit4serverauth.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private long orderDetailId;
    private long orderId;
    private long menuId;
    private long quantity;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
}
