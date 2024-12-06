package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.service.OrderService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/top_stores")
    public ResponseEntity<?> getCategoryPopularStores(
            @RequestParam(required = false, defaultValue = "*") String category,
            @RequestParam(required = false, defaultValue = "5") int limit) {
        return orderService.getCategoryPopularStores(category, limit);
    }
}
