package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public List<Map<String, Object>> getMenuAndStoresByKeyword(String keyword) {
        return menuRepository.findMenuAndStoresByKeyword(keyword);
    }
}
