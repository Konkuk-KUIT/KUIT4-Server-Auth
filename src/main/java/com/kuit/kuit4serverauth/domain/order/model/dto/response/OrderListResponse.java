package com.kuit.kuit4serverauth.domain.order.model.dto.response;

import com.kuit.kuit4serverauth.domain.order.model.dao.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class OrderListResponse {
    private final List<Order> orders;
}
