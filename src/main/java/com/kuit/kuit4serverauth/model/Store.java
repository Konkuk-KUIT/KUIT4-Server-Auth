package com.kuit.kuit4serverauth.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class Store {
    private Long storeId;
    private String storeName;
    private String address;
    private String phone;
    private String contents;
    private int minOrderPrice;
    private int deliveryPrice;
    private int rate;
    private String category;
    private String operationHour;
    private String closedDay;
    private String deliveryArea;
    private int dibCount;
    private Timestamp created_at;
    private Timestamp updated_at;
    private String status;
    private int predictedDeliveryTime;
}
