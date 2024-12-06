package com.kuit.kuit4serverauth.DTO.Response.Menu;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuSearchResponse {
    private String menu_name;
    private String store_name;
}
