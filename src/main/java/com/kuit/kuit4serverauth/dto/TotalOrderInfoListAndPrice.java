package com.kuit.kuit4serverauth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Schema(description = "전체 주문 정보에 대한 객체")
public class TotalOrderInfoListAndPrice {

    @Schema(description = "전체 주문 금액", example = "25000")
    private final long totalPrice;

    @Schema(description = "전체 주문 상세 목록")
    private final List<TotalOrderInfo> totalOrderInfoList;
}
