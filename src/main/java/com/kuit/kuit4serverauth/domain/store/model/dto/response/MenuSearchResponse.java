package com.kuit.kuit4serverauth.domain.store.model.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MenuSearchResponse {
    private final String storeName;
    private final String menuName;
}
