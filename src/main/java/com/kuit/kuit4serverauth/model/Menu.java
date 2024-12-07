package com.kuit.kuit4serverauth.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Menu {
    private Long menu_id;
    private String name;
    private int price;
    private Long store_id;  // JPA 였다면 Store store 로 매핑했을 듯
}
