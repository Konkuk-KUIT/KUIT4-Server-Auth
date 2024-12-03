package com.kuit.kuit4serverauth.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class Order {
    private Long orderId;
    private int total_price;
    private String payMethod;
    private String requirement;
    private Timestamp created_at;
    private Timestamp updated_at;
    private String status;
}
