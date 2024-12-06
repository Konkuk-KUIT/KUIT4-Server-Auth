package com.kuit.kuit4serverauth.DTO.Response.Store;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreResponse {
    private Long store_id;
    private String name;
    private Integer minimum_delivery_price;
    private String status;
}