package com.kuit.kuit4serverauth.dto;

import com.kuit.kuit4serverauth.model.Menu;
import com.kuit.kuit4serverauth.model.Store;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuAndStore {
    private Menu menu;
    private Store store;
}
