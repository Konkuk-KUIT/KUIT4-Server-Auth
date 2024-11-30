package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/search")
    public ResponseEntity<?> getStores(
            @RequestParam(required = false, defaultValue = "0") int minPrice,
            @RequestParam(required = false, defaultValue = "active") String status) {
        return storeService.getStores(minPrice, status);
    }
}
