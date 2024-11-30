package com.kuit.kuit4serverauth.DTO.Response.Menu;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuResponse {
    private Long menu_id;
    private String menu_name;
    private List<MenuOptionResponse> menu_option;  // 메뉴 옵션 목록
}
