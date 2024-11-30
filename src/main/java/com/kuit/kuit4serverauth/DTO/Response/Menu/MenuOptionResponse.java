package com.kuit.kuit4serverauth.DTO.Response.Menu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuOptionResponse {
    private Long menu_option_id;
    private String essential_option;
    private String select_option;
    private int price;
}
