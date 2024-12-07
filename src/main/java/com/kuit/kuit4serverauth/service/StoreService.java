package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.model.Store;
import com.kuit.kuit4serverauth.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public List<Store> findStoresByConditions(Integer minimumOrderPrice, String status){
        return storeRepository.findByMinimumOrderPriceAndStatus(minimumOrderPrice, status);
    }

}
