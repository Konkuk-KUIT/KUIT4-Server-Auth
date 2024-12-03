package com.kuit.kuit4serverauth.domain.store.controller;

import com.kuit.kuit4serverauth.domain.store.model.dto.response.CategorySearchResponse;
import com.kuit.kuit4serverauth.domain.store.model.dto.response.MenuSearchResponse;
import com.kuit.kuit4serverauth.domain.store.model.dto.response.StoreResponse;
import com.kuit.kuit4serverauth.domain.store.service.StoreService;
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
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/{minDeliveryPrice}/min-price")
    public BaseResponse<List<StoreResponse>> searchByMinDeliveryPrice(@PathVariable int minDeliveryPrice) {
        log.info("음식점 조회");
        return BaseResponse.ok(storeService.searchByMinDeliveryPrice(minDeliveryPrice));
    }

    @GetMapping("/{menu}/menu")
    public BaseResponse<List<MenuSearchResponse>> searchByMenu(@PathVariable String menu) {
        log.info("특정 문자열이 포함된 음식점 조회");
        return BaseResponse.ok(storeService.searchByMenu(menu));
    }

    @GetMapping("/{category}/category")
    public BaseResponse<List<CategorySearchResponse>> searchByCategory(@PathVariable String category) {
        log.info("카테고리별 인기 음식점 조회");
        return BaseResponse.ok(storeService.searchByCategory(category));
    }

    @GetMapping("/{userId}/twice-order")
    public BaseResponse<List<StoreResponse>> searchForUserOrderTwiceStores(@PathVariable Long userId) {
        log.info("특정 회원이 2번 이상 주문한 음식점 조회");
        return BaseResponse.ok(storeService.searchForUserOrderTwiceStores(userId));
    }
}
