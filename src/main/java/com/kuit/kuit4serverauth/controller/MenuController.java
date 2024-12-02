package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.MenuAndStore;
import com.kuit.kuit4serverauth.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    @GetMapping("/keyword")
    public List<MenuAndStore> getMenuAndStoresContainKeyword(@RequestParam("keyword") String keyword){
        return menuService.getMenuAndStoresContainKeyword(keyword);
    }
}
