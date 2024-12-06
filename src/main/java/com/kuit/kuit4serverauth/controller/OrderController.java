package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.OrderListResDto;
import com.kuit.kuit4serverauth.dto.RestaurantResDto;
import com.kuit.kuit4serverauth.dto.TwiceOrderedResDto;
import com.kuit.kuit4serverauth.enums.Category;
import com.kuit.kuit4serverauth.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 기능 요구사항 3 : 특정 회원이 두번 이상 주문한 음식점 조회 (중복 제거)
    @GetMapping("/{userId}/twice-ordered")
    public ResponseEntity<List<TwiceOrderedResDto>> getTwiceOrderedRestaurant(@PathVariable("userId") Long userId) {
        List<TwiceOrderedResDto> response = orderService.getTwiceOrderedRestaurant(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 기능 요구사항 4 : 카테고리별 인기 음식점 조회 (상위 10개 반환)
    @GetMapping("/category-top/{category}")
    public ResponseEntity<List<RestaurantResDto>> getTopRestaurantsByCategory(@PathVariable("category") Category category) {
        List<RestaurantResDto> response = orderService.getTopRestaurantsByCategory(category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 기능 요구사항 5 : 특정 회원의 주문 내역 조회 (가격 순으로 정렬)
    @GetMapping("/{userId}/order-list")
    public ResponseEntity<List<OrderListResDto>> getUserOrderList(@PathVariable("userId") Long userId) {
        List<OrderListResDto> response = orderService.getUserOrderList(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
