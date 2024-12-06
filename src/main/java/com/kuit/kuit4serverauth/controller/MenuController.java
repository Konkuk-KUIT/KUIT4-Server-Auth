package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/menus")
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/search")
    public ResponseEntity<?> getMenuAndStoresByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(menuService.getMenuAndStoresByKeyword(keyword));
    }
}
