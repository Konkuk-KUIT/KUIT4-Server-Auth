package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.DTO.Response.ApiResponse;
import com.kuit.kuit4serverauth.DTO.Response.OrderResponse;
import com.kuit.kuit4serverauth.DTO.Response.Store.FrequentStoreResponse;
import com.kuit.kuit4serverauth.DTO.Response.Store.PopularCategortStoreResponse;
import com.kuit.kuit4serverauth.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public ResponseEntity<?> getFrequentStores(Long userId) {
        List<FrequentStoreResponse> Responses = orderRepository.findFrequentStoresByUserId(userId);
        if (Responses.isEmpty()) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(404, "조건에 맞는 가게가 없습니다.", Responses)
            );
        }
        return ResponseEntity.ok(
                new ApiResponse<>(200, "요청에 성공하였습니다.", Responses)
        );
    }

    public ResponseEntity<?> getCategoryPopularStores(String category, int limit) {
        List<PopularCategortStoreResponse> Responses = orderRepository.findPopularStoresByCategory(category, limit);
        if (Responses.isEmpty()) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(404, "조건에 맞는 가게가 없습니다.", Responses)
            );
        }
        return ResponseEntity.ok(
                new ApiResponse<>(200, "요청에 성공하였습니다.", Responses)
        );
    }

    public ResponseEntity<?> getUserOrderHistory(Long userId) {
        List<OrderResponse> Responses = orderRepository.findOrdersByUserId(userId);
        if (Responses.isEmpty()) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(404, "조건에 맞는 메뉴가 없습니다.", Responses)
            );
        }
        return ResponseEntity.ok(
                new ApiResponse<>(200, "요청에 성공하였습니다.", Responses)
        );
    }
}
