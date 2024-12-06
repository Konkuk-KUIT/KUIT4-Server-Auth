package com.kuit.kuit4serverauth.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuAndStoreResponse {
    private Long menuId;
    private String menuName;
    private Long storeId;
    private String storeName;
}
