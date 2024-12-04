package com.kuit.kuit4serverauth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "메뉴 옵션 정보에 대한 객체")
public class MenuOption {
    @Schema(description = "메뉴 옵션 ID", example = "1")
    private long menuOptionId;

    @Schema(description = "연관 메뉴 ID", example = "1")
    private long menuId;

    @Schema(description = "메뉴 옵션 이름", example = "Cheese")
    private String name;

    @Schema(description = "필수 옵션 여부", example = "true")
    private boolean required;

    @Schema(description = "다중 선택 가능 여부", example = "false")
    private boolean multi;

    @Schema(description = "생성 일시", example = "2024-12-01T12:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정 일시", example = "2024-12-02T12:00:00")
    private LocalDateTime updatedAt;

    @Schema(description = "상태", example = "active")
    private String status;
}
