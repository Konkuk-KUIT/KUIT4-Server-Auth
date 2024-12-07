package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.OrderInfoDTO;
import com.kuit.kuit4serverauth.dto.UserInfo;
import com.kuit.kuit4serverauth.model.Store;
import com.kuit.kuit4serverauth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(UserInfo userInfo) {
        if (userInfo.getUsername() != null) {
            return ResponseEntity.ok("Hello, " + userInfo.getUsername());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin(UserInfo userInfo) {
        if (userInfo.getRole() != null && userInfo.getRole().equals("ROLE_ADMIN")) {
            return ResponseEntity.ok("Hello, admin");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
    }

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<List<OrderInfoDTO>> getUsersAllOrders(@PathVariable Long userId) {
        List<OrderInfoDTO> usersAllOrders = userService.findUsersAllOrders(userId);
        return ResponseEntity.ok(usersAllOrders);
    }

    @GetMapping("/users/{userId}/ordered-stores")
    public ResponseEntity<List<Store>> getStoresWithMultipleOrders(@PathVariable Long userId,
                                                                   @RequestParam("minOrderCount") int minOrderCount) {
        List<Store> stores = userService.findStoresWithMultipleOrders(userId, minOrderCount);
        return ResponseEntity.ok(stores);
    }
}
