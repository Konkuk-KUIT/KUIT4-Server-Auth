package com.kuit.kuit4serverauth.domain.user.controller;

import com.kuit.kuit4serverauth.domain.user.model.dto.request.UserInfoRequest;
import com.kuit.kuit4serverauth.global.response.BaseResponse;
import com.kuit.kuit4serverauth.global.response.exception.CustomException;
import com.kuit.kuit4serverauth.global.response.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    @GetMapping("/profile")
    public BaseResponse<String> getProfile(UserInfoRequest userDto) {
        log.info("사용자 {} 프로필 요청", userDto.getUsername());
//        if(userDto.getUsername() == null) { //여기 예외처리 어떻게??
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
//        }
        return BaseResponse.ok("Hello, " + userDto.getUsername());
    }

    @GetMapping("/admin")
    public BaseResponse<String> getAdmin(UserInfoRequest userDto) {
        log.info("사용자 {} 관리자 요청", userDto.getUsername());
        if (Objects.equals(userDto.getRole(), "ROLE_ADMIN")) {
            return BaseResponse.ok("Hello, admin");
        }
        throw new CustomException(ErrorCode.FORBIDDEN_ACCESS);
    }
}
