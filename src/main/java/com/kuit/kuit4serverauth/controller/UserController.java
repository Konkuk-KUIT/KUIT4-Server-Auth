package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.model.dto.request.UserInfoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Slf4j
public class UserController {

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(UserInfoRequest userDto) {
        log.info("사용자 {} 프로필 요청", userDto.getUsername());
//        if(request.getAttribute("username") == null) { //여기 예외처리 어떻게??
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
//        }
        return ResponseEntity.ok("Hello, " + userDto.getUsername());
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin(UserInfoRequest userDto) {
        log.info("사용자 {} 관리자 요청", userDto.getUsername());
        if (Objects.equals(userDto.getRole(), "ROLE_ADMIN")) {
            return ResponseEntity.ok("Hello, admin");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
    }
}
