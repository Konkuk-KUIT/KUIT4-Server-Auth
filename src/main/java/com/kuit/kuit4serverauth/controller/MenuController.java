package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.MenuStoreDTO;
import com.kuit.kuit4serverauth.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/menus")
    public ResponseEntity<List<MenuStoreDTO>> search(@RequestParam("menuName") String menuName) {
        List<MenuStoreDTO> list = menuService.findMenuAndStoreNameByMenuName(menuName);

        return ResponseEntity.ok(list);
    }
}
