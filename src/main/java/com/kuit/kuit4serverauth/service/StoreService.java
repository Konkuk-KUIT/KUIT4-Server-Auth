package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.model.Store;
import com.kuit.kuit4serverauth.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }

    public List<Store> getStoresFilteredByMinOrder(int minOrder, String status) {
        return storeRepository.filterByMinOrder(minOrder, status);
    }

    public Store findTopStoreByCategory(String category){
        return storeRepository.findTopOrderedByCategory(category);
    }

    public List<Store> findStoresUserMultipleOrdered(long userId){
        return storeRepository.findUserMultipleOrdered(userId);
    }
}
