package com.kuit.kuit4serverauth.controller;


import com.kuit.kuit4serverauth.dto.OrderDetail;
import com.kuit.kuit4serverauth.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;

    @GetMapping("/orders/{userid}")
    public ResponseEntity<List<OrderDetail>> findOrderDetailByUserId(@PathVariable("userid") Long userid) {
        List<OrderDetail> orderDetails = orderRepository.findOrderByUserId(userid);
        return ResponseEntity.ok(orderDetails);
    }
}
