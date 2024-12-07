package com.kuit.kuit4serverauth.domain.store.model.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StoreResponse {
    private final String name;
    private final String category;
    private final String phoneNum;
    private final int minDeliveryPrice;
}
