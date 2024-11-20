package com.kuit.kuit4serverauth.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(HttpServletRequest request) {
        // TODO : 로그인 한 사용자면 username 이용해 "Hello, {username}" 반환하기
        final String hello = "Hello, ";
        String userNameFromBody = request.getAttribute("username").toString();

        return ResponseEntity.status(HttpStatus.OK).body(hello + userNameFromBody);
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin(HttpServletRequest request) {
        // TODO: role이 admin이면 "Hello, admin" 반환하기
        String role = request.getAttribute("role").toString();
        System.out.println(role);

        if(role.equals("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.OK).body("admin confirmed");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
    }
}
