package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.DTO.Response.ApiResponse;
import com.kuit.kuit4serverauth.DTO.Response.MenuSearchResponse;

import com.kuit.kuit4serverauth.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public ResponseEntity<?> getMenuAndStores(String keyword) {
        List<MenuSearchResponse> Responses = menuRepository.findStoresAndMenuByKeyword(keyword);

        if (Responses.isEmpty()) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(404, "조건에 맞는 가게 및 메뉴가 없습니다.", Responses)
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(200, "요청에 성공하였습니다.", Responses)
        );
    }

}
