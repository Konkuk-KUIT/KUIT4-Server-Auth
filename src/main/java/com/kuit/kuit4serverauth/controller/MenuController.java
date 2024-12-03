package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.MenuAndStore;
import com.kuit.kuit4serverauth.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MenuController {

    private final MenuRepository menuRepository;
    @GetMapping("/menus")
    public ResponseEntity<List<MenuAndStore>> findMenuAndStoresByName(@RequestParam String name) {
        List<MenuAndStore> menuAndStores = menuRepository.findMenuByName(name);
        return ResponseEntity.ok(menuAndStores);
    }
}
