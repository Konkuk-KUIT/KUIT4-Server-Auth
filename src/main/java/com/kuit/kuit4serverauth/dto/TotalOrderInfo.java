package com.kuit.kuit4serverauth.dto;

import com.kuit.kuit4serverauth.model.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Schema(description = "주문 정보와 그에 대한 상세 정보를 포함하는 객체")
public class TotalOrderInfo {

    @Schema(description = "주문 정보")
    private final Order order;

    @Schema(description = "주문 상세 정보")
    private final OrderDetail orderDetail;

    @Schema(description = "메뉴 정보")
    private final Menu menu;

    @Schema(description = "메뉴 옵션 정보")
    private final MenuOption menuOption;

    @Schema(description = "메뉴 옵션 세부사항 정보")
    private final MenuOptionDetail menuOptionDetail;
}
