package com.kuit.kuit4serverauth.model;

import com.kuit.kuit4serverauth.enums.Category;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class Restaurant {

    private Long restaurantId;
    private String restaurantName;
    private int leastDeliveryPrice;
    @Builder.Default
    private String status = "ACTIVATE";
    private Category category;
}
