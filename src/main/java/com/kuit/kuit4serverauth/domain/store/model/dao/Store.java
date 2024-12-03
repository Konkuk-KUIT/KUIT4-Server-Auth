package com.kuit.kuit4serverauth.domain.store.model.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Store {
    private Long store_id;
    private String name;
    private String address; //point를 문자열로 파싱
    private String category;
    private String phoneNum;
    private int minDeliveryPrice;
}
