package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.dto.RestaurantMenuResDto;
import com.kuit.kuit4serverauth.dto.RestaurantResDto;
import com.kuit.kuit4serverauth.enums.Category;
import com.kuit.kuit4serverauth.model.Restaurant;
import com.kuit.kuit4serverauth.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<RestaurantResDto> getRestaurantByLeastDeliveryPrice(String leastPrice) {

        List<Restaurant> restaurants = restaurantRepository.findByLeastPriceAndStatus(leastPrice);

        return restaurants.stream()
                .map(restaurant -> RestaurantResDto.builder()
                        .restaurantId(restaurant.getRestaurantId())
                        .restaurantName(restaurant.getRestaurantName())
                        .leastDeliveryPrice(restaurant.getLeastDeliveryPrice())
                        .category(restaurant.getCategory())
                        .status(restaurant.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    public List<RestaurantMenuResDto> getRestaurantByContainingMenu(String menu) {
        return restaurantRepository.findMenusAndRestaurantsByMenuName(menu).stream()
                .map(row -> RestaurantMenuResDto.builder()
                        .restaurantId(((Number) row.get("restaurantId")).longValue())
                        .restaurantName((String) row.get("restaurantName"))
                        .leastDeliveryPrice((Integer) row.get("leastDeliveryPrice"))
                        .status((String) row.get("status"))
                        .category(Category.valueOf((String)row.get("category")))
                        .menuId(((Number) row.get("menuId")).longValue())
                        .menuName((String) row.get("menuName"))
                        .build()
                )
                .toList();
    }


}
