package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.MenuAndStore;
import com.kuit.kuit4serverauth.dto.TotalOrderInfo;
import com.kuit.kuit4serverauth.dto.TotalOrderInfoListAndPrice;
import com.kuit.kuit4serverauth.model.User;
import com.kuit.kuit4serverauth.service.UserService;
import com.kuit.kuit4serverauth.util.AccessUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User APIs", description = "유저 관련 API")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{userId}/ordered-info")
    @Operation(
            summary = "회원 주문 내역 조회",
            description = "- 특정 회원의 주문 내역을 조회하며, 가격 순으로 정렬합니다.\n" +
                    "- 메뉴와 옵션 정보를 포함하고 총 가격(`totalPrice`)을 계산하여 반환합니다.",

            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공적으로 메뉴와 음식점 정보를 반환합니다."
                    )
            }
    )
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
