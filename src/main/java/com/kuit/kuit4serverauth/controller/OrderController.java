package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/conditional-history")
    public ResponseEntity<?> getMemberOrderHistory(@RequestParam Long memberId) {
        return ResponseEntity.ok(orderService.getMemberOrderHistory(memberId));
    }
}
