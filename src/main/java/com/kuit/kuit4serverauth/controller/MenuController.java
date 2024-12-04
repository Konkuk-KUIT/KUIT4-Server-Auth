package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.MenuAndStore;
import com.kuit.kuit4serverauth.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menus")
@Tag(name = "Menu APIs", description = "메뉴와 관련된 API")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    @GetMapping("/keyword")
    @Operation(
            summary = "특정 문자열이 포함된 메뉴와 음식점 조회",
            description = "이름에 keyword가 포함된 메뉴와 해당 음식점 정보를 반환합니다."
    )
    public List<MenuAndStore> getMenuAndStoresContainKeyword(@RequestParam("keyword") String keyword){
        return menuService.getMenuAndStoresContainKeyword(keyword);
    }
}
