package com.kuit.kuit4serverauth.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OrderListResDto {

    private Long orderId;
    private LocalDateTime orderDate;
    private String restaurantName;
    private Long menuId;
    private String menuName;
    private String optionName;
    private Integer optionPrice;
    private Double totalPrice;
    private Long userId;

}
