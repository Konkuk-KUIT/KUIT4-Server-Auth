package com.kuit.kuit4serverauth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "주문 정보에 대한 객체")
public class Order {

    @Schema(description = "주문 ID", example = "1")
    private long orderId;

    @Schema(description = "사용자 ID", example = "123")
    private long userId;

    @Schema(description = "가게 ID", example = "456")
    private long storeId;

    @Schema(description = "주문 금액", example = "15000")
    private long price;

    @Schema(description = "생성 일시", example = "2024-12-01T12:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정 일시", example = "2024-12-02T12:00:00")
    private LocalDateTime updatedAt;

    @Schema(description = "상태", example = "active")
    private String status;
}
