package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.dto.OrderListResDto;
import com.kuit.kuit4serverauth.dto.RestaurantResDto;
import com.kuit.kuit4serverauth.dto.TwiceOrderedResDto;
import com.kuit.kuit4serverauth.enums.Category;
import com.kuit.kuit4serverauth.model.Restaurant;
import com.kuit.kuit4serverauth.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<TwiceOrderedResDto> getTwiceOrderedRestaurant(Long userId) {

        List<Restaurant> restaurants = orderRepository.findTwiceOrderedRestaurant(userId);

        return restaurants.stream()
                .map(restaurant -> TwiceOrderedResDto.builder()
                        .userId(userId)
                        .restaurantId(restaurant.getRestaurantId())
                        .restaurantName(restaurant.getRestaurantName())
                        .leastDeliveryPrice(restaurant.getLeastDeliveryPrice())
                        .status(restaurant.getStatus())
                        .category(restaurant.getCategory())
                        .build())
                .collect(Collectors.toList());
    }

    public List<RestaurantResDto> getTopRestaurantsByCategory(Category category) {
        List<Restaurant> restaurants = orderRepository.findTopRestaurantsByCategory(category.toDbValue(), 10);
        return restaurants.stream()
                .map(restaurant -> RestaurantResDto.builder()
                        .restaurantId(restaurant.getRestaurantId())
                        .restaurantName(restaurant.getRestaurantName())
                        .leastDeliveryPrice(restaurant.getLeastDeliveryPrice())
                        .status(restaurant.getStatus())
                        .category(restaurant.getCategory())
                        .build())
                .collect(Collectors.toList());
    }

    public List<OrderListResDto> getUserOrderList(Long userId) {
        List<OrderListResDto> restaurants = orderRepository.findOrdersByMemberId(userId);
        return restaurants.stream()
                .map(restaurant -> OrderListResDto.builder()
                        .userId(restaurant.getUserId())
                        .orderId(restaurant.getOrderId())
                        .orderDate(restaurant.getOrderDate())
                        .restaurantName(restaurant.getRestaurantName())
                        .menuName(restaurant.getMenuName())
                        .optionName(restaurant.getOptionName())
                        .optionPrice(restaurant.getOptionPrice())
                        .totalPrice(restaurant.getTotalPrice())
                        .build())
                .collect(Collectors.toList());
    }



}
