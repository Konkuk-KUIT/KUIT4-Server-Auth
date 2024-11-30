package com.kuit.kuit4serverauth.DTO.Response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuSearchResponse {
    private String menuName;
    private String storeName;
}
