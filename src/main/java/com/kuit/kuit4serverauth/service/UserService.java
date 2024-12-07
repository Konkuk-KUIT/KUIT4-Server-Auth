package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.dto.OrderInfoDTO;
import com.kuit.kuit4serverauth.model.Store;
import com.kuit.kuit4serverauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<OrderInfoDTO> findUsersAllOrders(Long userId) {
        return userRepository.findUsersAllOrders(userId);
    }

    public List<Store> findStoresWithMultipleOrders(Long userId, int minOrderCount) {
        return userRepository.findStoresWithMultipleOrders(userId, minOrderCount);
    }
}
