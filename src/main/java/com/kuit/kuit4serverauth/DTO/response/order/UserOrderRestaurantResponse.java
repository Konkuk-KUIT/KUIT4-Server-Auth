package com.kuit.kuit4serverauth.DTO.response.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOrderRestaurantResponse {
    private String userId;
    private String restaurantId;
    private int orderNum;
}
