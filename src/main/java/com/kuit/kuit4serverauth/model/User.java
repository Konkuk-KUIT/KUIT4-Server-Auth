package com.kuit.kuit4serverauth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "사용자 정보에 대한 객체")
public class User {

    @Schema(description = "사용자 ID", example = "1")
    private long userId;

    @Schema(description = "사용자 이름", example = "user")
    private String username;

    @Schema(description = "비밀번호", example = "user123")
    private String password;

    @Schema(description = "권한", example = "ROLE_USER")
    private String role; // e.g., ROLE_USER, ROLE_ADMIN

    @Schema(description = "이메일", example = "user1@naver.com")
    private String email;

    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phone;

    @Schema(description = "생성 시간", example = "2023-12-02T15:30:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정 시간", example = "2023-12-02T16:00:00")
    private LocalDateTime updatedAt;

    @Schema(description = "상태", example = "active")
    private String status;

    // 생성자 및 static 팩토리 메서드
    private User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public static User of(String username, String role) {
        return new User(username, role);
    }
}
