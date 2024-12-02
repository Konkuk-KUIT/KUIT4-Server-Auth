package com.kuit.kuit4serverauth.dto;

import com.kuit.kuit4serverauth.model.Menu;
import com.kuit.kuit4serverauth.model.Store;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MenuAndStore {
    private final Menu menu;
    private final Store store;
}
