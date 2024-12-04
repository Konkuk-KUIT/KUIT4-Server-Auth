package com.kuit.kuit4serverauth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "메뉴 옵션 세부사항 정보에 대한 객체")
public class MenuOptionDetail {

    @Schema(description = "메뉴 옵션 세부사항 ID", example = "1")
    private long menuOptionDetailId;

    @Schema(description = "연관 메뉴 옵션 ID", example = "1")
    private long menuOptionId;

    @Schema(description = "세부사항 이름", example = "Cheddar")
    private String name;

    @Schema(description = "추가 가격", example = "0")
    private long additionalPrice;

    @Schema(description = "생성 일시", example = "2024-12-01T12:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정 일시", example = "2024-12-02T12:00:00")
    private LocalDateTime updatedAt;

    @Schema(description = "상태", example = "active")
    private String status;
}
