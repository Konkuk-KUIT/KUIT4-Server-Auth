package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.service.OrderService;
import com.kuit.kuit4serverauth.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        return userService.getProfile(username);
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin(HttpServletRequest request) {
        String role = (String) request.getAttribute("role");
        return userService.getAdmin(role);
    }

    @GetMapping("/{userId}/frequent-stores")
    public ResponseEntity<?> getFrequentStores(@PathVariable Long userId) {
        return orderService.getFrequentStores(userId);
    }

    @GetMapping("/{userId}/order-history")
    public ResponseEntity<?> getUserOrderHistory(@PathVariable Long userId) {
        return orderService.getUserOrderHistory(userId);
    }
}
