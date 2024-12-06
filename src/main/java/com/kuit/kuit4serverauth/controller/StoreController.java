package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.FrequentlyOrderedStore;
import com.kuit.kuit4serverauth.dto.PopularStore;
import com.kuit.kuit4serverauth.model.Store;
import com.kuit.kuit4serverauth.repository.OrderRepository;
import com.kuit.kuit4serverauth.repository.StoreRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class StoreController {

    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;
    @GetMapping("/stores")
    public ResponseEntity<List<Store>> getStores(
            @RequestParam("minorderprice") int minOrderPrice) {
        List<Store> stores = storeRepository.findByMinOrderPrice(minOrderPrice);
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/stores/{category}")
    public ResponseEntity<List<PopularStore>> getPopularStores(@PathVariable String category) {
        List<PopularStore> popularStores = storeRepository.findPopularStores(category);
        return ResponseEntity.ok(popularStores);
    }



}
