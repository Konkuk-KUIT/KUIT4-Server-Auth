package com.kuit.kuit4serverauth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "메뉴 정보 포함 객체")
public class Menu {

    @Schema(description = "메뉴 ID", example = "789")
    private long menuId;

    @Schema(description = "연관된 가게 ID", example = "456")
    private long storeId;

    @Schema(description = "메뉴 이름", example = "Burger")
    private String name;

    @Schema(description = "메뉴 가격", example = "7500")
    private long price;

    @Schema(description = "생성 일시", example = "2024-12-01T12:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정 일시", example = "2024-12-02T12:00:00")
    private LocalDateTime updatedAt;

    @Schema(description = "상태", example = "active")
    private String status;
}
