package com.kuit.kuit4serverauth.domain.order.service;

import com.kuit.kuit4serverauth.domain.order.model.dao.Order;
import com.kuit.kuit4serverauth.domain.store.model.dao.Store;
import com.kuit.kuit4serverauth.domain.order.model.dto.response.OrderListResponse;
import com.kuit.kuit4serverauth.domain.store.model.dto.response.StoreResponse;
import com.kuit.kuit4serverauth.domain.order.repository.OrderRepository;
import com.kuit.kuit4serverauth.global.response.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.kuit.kuit4serverauth.global.response.exception.ErrorCode.NO_SUCH_ORDER;
import static com.kuit.kuit4serverauth.global.response.exception.ErrorCode.NO_SUCH_STORE;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderListResponse searchForOrderList(Long userId) {
        List<Order> orders = orderRepository.searchByUserId(userId);
        if(orders.isEmpty()) {
            throw new CustomException(NO_SUCH_ORDER);
        }
        return new OrderListResponse(orders);
    }
}
