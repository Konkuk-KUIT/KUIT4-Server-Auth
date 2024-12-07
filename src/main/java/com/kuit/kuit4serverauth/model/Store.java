package com.kuit.kuit4serverauth.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {
    private Long store_id;
    private String name;
    private Integer minimum_order_price;
    private String status;
    private Long category_id;

    // JPA 였다면
    // Category category
}
