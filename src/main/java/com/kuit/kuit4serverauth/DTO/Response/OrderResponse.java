package com.kuit.kuit4serverauth.DTO.Response;

import com.kuit.kuit4serverauth.DTO.Response.Menu.MenuResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private Long order_id;
    private String payment;
    private int total_price;
    private List<MenuResponse> menu_list;  // 주문에 포함된 메뉴 목록
}
