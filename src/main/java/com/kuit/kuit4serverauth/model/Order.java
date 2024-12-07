package com.kuit.kuit4serverauth.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private Long order_id;
    private int total_price;
    private Long store_id;
    private Long menu_id;
    private Long user_id;

    // JPA 였다면
    // Store store
    // Menu menu
    // User user

    // 혹은
    // Store store
    // List<OrderMenu> orderMenuList
    // User user
}
