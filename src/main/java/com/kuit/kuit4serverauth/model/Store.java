package com.kuit.kuit4serverauth.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Store {
    private Long storeID;
    private String storeName;
    private int minOrderPrice;
    private String category;
    private String status;

}
