package com.kuit.kuit4serverauth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class TotalOrderInfoListAndPrice {
    private final long totalPrice;
    private final List<TotalOrderInfo> totalOrderInfoList;
}
