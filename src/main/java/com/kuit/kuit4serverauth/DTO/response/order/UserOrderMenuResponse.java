package com.kuit.kuit4serverauth.DTO.response.order;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserOrderMenuResponse {
    private String menu;
    private String restaurant;
    private int total_price;
}
