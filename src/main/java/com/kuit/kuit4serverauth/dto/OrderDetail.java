package com.kuit.kuit4serverauth.dto;

import com.kuit.kuit4serverauth.model.Menu;
import com.kuit.kuit4serverauth.model.MenuOption;
import com.kuit.kuit4serverauth.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetail {
    private Long orderID;
    private int total_price;
    private Long menuID;
    private String menuName;
    private Long menuOptionID;
    private String option;
}
