package com.kuit.kuit4serverauth.DTO.Response.Store;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopularCategortStoreResponse {
    private Long store_id;
    private String store_name;
    private int order_count;
}
