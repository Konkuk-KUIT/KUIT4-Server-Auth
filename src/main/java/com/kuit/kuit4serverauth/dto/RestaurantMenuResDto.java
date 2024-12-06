package com.kuit.kuit4serverauth.dto;

import com.kuit.kuit4serverauth.enums.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestaurantMenuResDto {

    private Long restaurantId;
    private String restaurantName;
    private int leastDeliveryPrice;
    private String status;
    private Category category;
    private Long menuId;
    private String menuName;

}
