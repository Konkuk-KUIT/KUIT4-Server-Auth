package com.kuit.kuit4serverauth.domain.order.model.dao;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Order {
    private String store_name;
    private int order_price;
    private int order_discount;
    private String order_item_name;
    private String order_number;
    private Timestamp ordered_date;
//    private int delivery_method;
    private String order_requirement;
    private int item_amount;
    private String option_group_name;
    private String option_name;
}