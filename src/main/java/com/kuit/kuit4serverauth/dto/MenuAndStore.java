package com.kuit.kuit4serverauth.dto;

import com.kuit.kuit4serverauth.model.Menu;
import com.kuit.kuit4serverauth.model.Store;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Schema(description = "메뉴와 음식점 정보를 담고 있는 객체")
public class MenuAndStore {
    private final Menu menu;
    private final Store store;
}
