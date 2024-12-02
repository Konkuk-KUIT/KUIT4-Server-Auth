package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.dto.MenuAndStore;
import com.kuit.kuit4serverauth.repository.MenuRepository;
import com.kuit.kuit4serverauth.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository, StoreRepository storeRepository){
        this.menuRepository = menuRepository;
        this.storeRepository = storeRepository;
    }

    public List<MenuAndStore> getMenuAndStoresContainKeyword(String keyword) {
        return menuRepository.findMenusAndStoresByKeyword(keyword);
    }
}
