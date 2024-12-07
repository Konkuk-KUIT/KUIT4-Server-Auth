package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.dto.MenuStoreDTO;
import com.kuit.kuit4serverauth.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuStoreDTO> findMenuAndStoreNameByMenuName(String menuName) {
        return menuRepository.findMenuAndStoreNameByMenuName(menuName);
    }
}
