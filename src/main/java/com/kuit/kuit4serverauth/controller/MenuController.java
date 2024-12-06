package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.service.MenuService;
import com.kuit.kuit4serverauth.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/menus/search")
    public ResponseEntity<?> getStores(
            @RequestParam String keyword) {
        return menuService.getMenuAndStores(keyword);
    }
}
