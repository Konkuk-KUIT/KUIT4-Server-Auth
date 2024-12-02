package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.TotalOrderInfo;
import com.kuit.kuit4serverauth.dto.TotalOrderInfoListAndPrice;
import com.kuit.kuit4serverauth.model.User;
import com.kuit.kuit4serverauth.service.UserService;
import com.kuit.kuit4serverauth.util.AccessUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kuit.kuit4serverauth.enums.Role.ROLE_USER;
import static com.kuit.kuit4serverauth.exception.ErrorCode.FORBIDDEN_ACCESS;
import static com.kuit.kuit4serverauth.exception.ErrorCode.INVALID_TOKEN;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{userId}/ordered-info")
    public TotalOrderInfoListAndPrice getTotalUserOrderedInfo(@PathVariable("userId") long userId){
        return userService.getTotalUserOrderedInfo(userId);
    }

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");

        if (username != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Hello, "+username);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(INVALID_TOKEN.getMessage());
    }

    @GetMapping("/profile2")
    public ResponseEntity<String> getProfile2(@AccessUser User user) {
        String username = user.getUsername();

        if (username != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Hi, "+username);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(INVALID_TOKEN.getMessage());
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin(HttpServletRequest request) {
        String role = (String) request.getAttribute("role");

        if (role.equals(ROLE_USER.getRole())) {
            return ResponseEntity.status(HttpStatus.OK).body("Hello, admin");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(FORBIDDEN_ACCESS.getMessage());
    }

    @GetMapping("/admin2")
    public ResponseEntity<String> getAdmin2(@AccessUser User user) {
        String role = user.getRole();

        if (role.equals(ROLE_USER.getRole())) {
            return ResponseEntity.status(HttpStatus.OK).body("Hi, admin");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(FORBIDDEN_ACCESS.getMessage());
    }
}
