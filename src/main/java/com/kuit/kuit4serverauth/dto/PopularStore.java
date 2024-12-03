package com.kuit.kuit4serverauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PopularStore {
    private String storename;
    private int ordercount;
}
