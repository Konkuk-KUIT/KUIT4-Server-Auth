package com.kuit.kuit4serverauth.dto;

import com.kuit.kuit4serverauth.enums.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TwiceOrderedResDto {

    private Long userId;
    private Long restaurantId;
    private String restaurantName;
    private int leastDeliveryPrice;
    private String status;
    private Category category;
}
