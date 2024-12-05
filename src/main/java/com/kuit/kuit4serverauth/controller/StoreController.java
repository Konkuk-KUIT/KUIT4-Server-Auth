package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.model.Store;
import com.kuit.kuit4serverauth.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/stores")
@Tag(name = "Store APIs", description = "가게 정보와 관련된 API")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping("/min-order")
    @Operation(
            summary = "최소 주문 금액 이상 음식점 조회",
            description = "최소 주문 금액 조건과 활성 상태(status) 조건을 만족하는 음식점을 조회합니다."
    )
    public List<Store> getStoresByMinOrder(@RequestParam("minOrder") int minOrder, @RequestParam("status") String status){
        return storeService.getStoresFilteredByMinOrder(minOrder, status);
    }

    @GetMapping("/category-top")
    @Operation(
            summary = "카테고리별 인기 음식점 조회",
            description = "특정 카테고리에서 주문이 가장 많은 상위 음식점을 조회합니다."
    )
    public Store getTopOrderedStoreForCategory(@RequestParam("category") String category){
        return storeService.findTopStoreByCategory(category);
    }

    @GetMapping("/user/{userId}/multiple-ordered")
    @Operation(
            summary = "회원의 조건부 주문 내역 조회",
            description = "특정 회원이 두 번 이상 주문한 음식점을 조회합니다. (중복 제거)."
    )
    public List<Store> findStoresUserMultipleOrdered(@PathVariable("userId") long userId){
        return storeService.findStoresUserMultipleOrdered(userId);
    }

    @GetMapping("/user/{userId}/multiple-ordered/paging")
    @Operation(
            summary = "회원의 조건부 주문 내역 조회 - 페이징",
            description = "특정 회원이 두 번 이상 주문한 음식점을 조회합니다. (중복 제거).\n" +
                    "쿼리 파라미터로 lastStoreId와 pageNum을 전달받아, " +
                    "lastStoreId 이후의 Store에 대해 pageNum 개수만큼 전달합니다."

    )
    public List<Store> findStoresUserMultipleOrderedPaging(
            @PathVariable("userId") long userId,
            @RequestParam("lastStoreId") long lastStoreId,
            @RequestParam("pageNum") int pageNum){
        return storeService.findStoresUserMultipleOrderedPaging(userId, lastStoreId, pageNum);
    }
}
