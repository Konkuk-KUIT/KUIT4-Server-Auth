package com.kuit.kuit4serverauth.DTO.Response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreResponse {
    private Long storeId;
    private String name;
    private Integer minimumDeliveryPrice;
    private String status;
}