package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public List<String> getMemberOrderHistory(Long memberId) {
        return orderRepository.findDistinctStoresByMemberId(memberId);
    }
}
