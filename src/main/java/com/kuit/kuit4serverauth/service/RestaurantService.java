package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public List<Map<String, Object>> getStoresByConditions(int minPrice, String status) {
        return restaurantRepository.findStoresByConditions(minPrice, status);
    }
}
