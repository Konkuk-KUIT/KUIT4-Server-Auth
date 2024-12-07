package com.kuit.kuit4serverauth.dto;

import com.kuit.kuit4serverauth.model.Order;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInfoDTO {

    Order order;
    int totalPrice;
    String storeName;
    String menuName;

}
