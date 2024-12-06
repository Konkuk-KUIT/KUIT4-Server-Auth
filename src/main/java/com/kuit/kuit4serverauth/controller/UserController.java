package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.FrequentlyOrderedStore;
import com.kuit.kuit4serverauth.interceptor.AuthInterceptor;
import com.kuit.kuit4serverauth.repository.StoreRepository;
import com.kuit.kuit4serverauth.service.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final StoreRepository storeRepository;
    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(HttpServletRequest request) {
        Object username = request.getAttribute("username");
        if (username != null) {
            return ResponseEntity.ok().body("Hello, " + username);
        }
        return ResponseEntity.status(UNAUTHORIZED).body("Unauthorized");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin(HttpServletRequest request) {
        Object role = request.getAttribute("role");
        if(role.equals("ROLE_ADMIN")) {
            return ResponseEntity.ok().body("Hello, admin");
        }
        return ResponseEntity.status(FORBIDDEN).body("Forbidden");
    }
    
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<FrequentlyOrderedStore>> getFrequentStores(@PathVariable Long userId) {
        List<FrequentlyOrderedStore> storeNameByUserId = storeRepository.findStoreNameByUserId(userId);
        return ResponseEntity.ok(storeNameByUserId);
    }
}
