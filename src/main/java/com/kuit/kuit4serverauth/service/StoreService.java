package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.DTO.Response.ApiResponse;
import com.kuit.kuit4serverauth.DTO.Response.Store.StoreResponse;
import com.kuit.kuit4serverauth.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public ResponseEntity<?> getStores(int minPrice, String status) {
        List<StoreResponse> stores = storeRepository.findStoresByMinPriceAndStatus(minPrice, status);

        if (stores.isEmpty()) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(404, "조건에 맞는 가게가 없습니다.", stores)
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(200, "요청에 성공하였습니다.", stores)
        );
    }

}
