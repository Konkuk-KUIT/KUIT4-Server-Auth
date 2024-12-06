package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stores")
public class RestaurantCOntroller {
    private final RestaurantService restaurantService;

    @GetMapping("/filter")
    public ResponseEntity<?> getStoresByConditions(
            @RequestParam(defaultValue = "0") int minPrice,
            @RequestParam(defaultValue = "active") String status) {
        return ResponseEntity.ok(restaurantService.getStoresByConditions(minPrice, status));
    }
}
