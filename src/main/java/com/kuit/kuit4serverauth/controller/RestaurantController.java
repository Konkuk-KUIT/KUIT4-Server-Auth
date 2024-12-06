package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.RestaurantMenuResDto;
import com.kuit.kuit4serverauth.dto.RestaurantResDto;
import com.kuit.kuit4serverauth.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    // 기능 요구사항 1 : 최소 주문 금액 이하 음식점 조회
    @GetMapping("/least-price")
    public ResponseEntity<List<RestaurantResDto>> getRestaurantByLeastDeliveryPrice(@RequestParam("leastPrice") String leastPrice) {
        List<RestaurantResDto> response = restaurantService.getRestaurantByLeastDeliveryPrice(leastPrice);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 기능 요구사항 2 : 특정 메뉴 이름이 포함된 메뉴와 음식점 조회
    @GetMapping("/containing-menu")
    public ResponseEntity<List<RestaurantMenuResDto>> getRestaurantByContainingMenu(@RequestParam("menu") String menu) {
        List<RestaurantMenuResDto> response = restaurantService.getRestaurantByContainingMenu(menu);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
