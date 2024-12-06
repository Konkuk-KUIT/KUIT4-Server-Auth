package com.kuit.kuit4serverauth.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Menu {

    private Long restaurantId;
    private Long menuId;
    private String name;
    private String price;
    private String optionName;
    private Integer optionPrice;
}
