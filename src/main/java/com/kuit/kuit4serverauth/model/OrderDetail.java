package com.kuit.kuit4serverauth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    @Schema(description = "주문 상세 ID", example = "1")
    private long orderDetailId;

    @Schema(description = "연관된 주문 ID", example = "1")
    private long orderId;

    @Schema(description = "연관된 메뉴 ID", example = "789")
    private long menuId;

    @Schema(description = "주문 수량", example = "2")
    private long quantity;

    @Schema(description = "생성 일시", example = "2024-12-01T12:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정 일시", example = "2024-12-02T12:00:00")
    private LocalDateTime updatedAt;

    @Schema(description = "상태", example = "active")
    private String status;
}
