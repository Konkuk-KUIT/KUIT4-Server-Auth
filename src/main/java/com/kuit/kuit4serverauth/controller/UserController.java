package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.UserInfo;
import com.kuit.kuit4serverauth.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

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
}
