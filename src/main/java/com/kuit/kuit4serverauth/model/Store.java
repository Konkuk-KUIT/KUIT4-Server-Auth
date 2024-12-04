package com.kuit.kuit4serverauth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "가게 정보에 대한 객체")
public class Store {
    @Schema(description = "가게 ID", example = "1")
    private long storeId;

    @Schema(description = "가게 이름", example = "김밥천국")
    private String name;

    @Schema(description = "카테고리", example = "한식")
    private String category;

    @Schema(description = "최소 주문 금액", example = "10000")
    private long minOrder;

    @Schema(description = "전화번호", example = "02-123-4567")
    private String telephone;

    @Schema(description = "주소", example = "서울특별시 강남구 강남대로 123")
    private String address;

    @Schema(description = "위도", example = "37.497909")
    private double latitude;

    @Schema(description = "경도", example = "127.027635")
    private double longitude;

    @Schema(description = "생성 일시", example = "2024-12-01T12:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정 일시", example = "2024-12-02T12:00:00")
    private LocalDateTime updatedAt;

    @Schema(description = "상태", example = "active")
    private String status;
}
