package com.kuit.kuit4serverauth.domain.order.controller;

import com.kuit.kuit4serverauth.domain.order.model.dto.response.OrderListResponse;
import com.kuit.kuit4serverauth.domain.store.model.dto.response.StoreResponse;
import com.kuit.kuit4serverauth.domain.order.service.OrderService;
import com.kuit.kuit4serverauth.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{userId}/order-list")
    public BaseResponse<OrderListResponse> searchForOrderListByUser(@PathVariable Long userId) {
        log.info("회원 주문 내역 조회");
        return BaseResponse.ok(orderService.searchForOrderList(userId));
    }

}
