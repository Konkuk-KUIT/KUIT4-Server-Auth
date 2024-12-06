package com.kuit.kuit4serverauth.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryPopularStoreResponse {
    private Long storeId;
    private String storeName;
    private int orderCount;
}
