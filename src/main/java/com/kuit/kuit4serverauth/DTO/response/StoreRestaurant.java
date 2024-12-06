package com.kuit.kuit4serverauth.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoreRestaurant {
    private int restaurantId;
    private String name;
    private int minPrice;
    private String status;
}
