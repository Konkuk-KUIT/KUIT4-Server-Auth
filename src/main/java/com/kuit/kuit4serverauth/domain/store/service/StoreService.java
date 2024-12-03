package com.kuit.kuit4serverauth.domain.store.service;

import com.kuit.kuit4serverauth.domain.store.model.dao.FirstStore;
import com.kuit.kuit4serverauth.domain.store.model.dao.Store;
import com.kuit.kuit4serverauth.domain.store.model.dao.StoreMenu;
import com.kuit.kuit4serverauth.domain.store.model.dto.response.CategorySearchResponse;
import com.kuit.kuit4serverauth.domain.store.model.dto.response.MenuSearchResponse;
import com.kuit.kuit4serverauth.domain.store.model.dto.response.StoreResponse;
import com.kuit.kuit4serverauth.domain.store.repository.StoreRepository;
import com.kuit.kuit4serverauth.global.response.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.kuit.kuit4serverauth.global.response.exception.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public List<StoreResponse> searchByMinDeliveryPrice(int minDeliveryPrice) {
        List<Store> findStores = storeRepository.findByMinDeliveryPrice(minDeliveryPrice);
        checkListEmpty(findStores);

        List<StoreResponse> storeResponse = buildStoreResponse(findStores);
        return storeResponse;
    }

    public List<MenuSearchResponse> searchByMenu(String menu) {
        log.info(menu);
        List<StoreMenu> findStoreMenus = storeRepository.findByMenu(menu);
        checkListEmpty(findStoreMenus);

        List<MenuSearchResponse> menuSearchResponse = new ArrayList<>();
        for (StoreMenu storeMenu : findStoreMenus) {
            MenuSearchResponse response = new MenuSearchResponse(storeMenu.getStoreName(), storeMenu.getMenuName());
            menuSearchResponse.add(response);
        }
        return menuSearchResponse;
    }

    public List<CategorySearchResponse> searchByCategory(String category) {
        List<FirstStore> findStores = storeRepository.findByCategory(category);
        checkListEmpty(findStores);

        List<CategorySearchResponse> categorySearchResponses = new ArrayList<>();
        for (FirstStore store : findStores) {
            CategorySearchResponse response = new CategorySearchResponse(
                    store.getName(), store.getCategory(), store.getOrderCounts());
            categorySearchResponses.add(response);
        }
        return categorySearchResponses;
    }

    public List<StoreResponse> searchForUserOrderTwiceStores(Long userId) {
        List<Store> findStores = storeRepository.searchForStore(userId);
        checkListEmpty(findStores);

        List<StoreResponse> storeResponse = buildStoreResponse(findStores);
        return storeResponse;
    }

    private void checkListEmpty(List findStores) {
        if(findStores.isEmpty()) {
            log.error("적절한 음식점이 없습니다.");
            throw new CustomException(NO_SUCH_STORE);
        }
    }

    private List<StoreResponse> buildStoreResponse(List<Store> findStores) {
        List<StoreResponse> storeResponse = new ArrayList<>();
        for (Store store : findStores) {
            StoreResponse response = new StoreResponse(
                    store.getName(), store.getCategory(), store.getPhoneNum(), store.getMinDeliveryPrice());
            storeResponse.add(response);
        }
        return storeResponse;
    }

}
