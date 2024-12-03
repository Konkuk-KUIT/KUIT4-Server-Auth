package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.model.Store;
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
@RequestMapping("/stores")
@Controller
public class StoreController {

    private final StoreRepository storeRepository;

    @GetMapping
    public ResponseEntity<List<Store>> getStores(
            @RequestParam("minOrderPrice") int minOrderPrice) {
        List<Store> stores = storeRepository.findByMinOrderPrice(minOrderPrice);
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/categories/{category}/popular")
    public ResponseEntity<List<Store>> getPopularStores(@PathVariable String category) {
        List<Store> popularStores = storeRepository.findPopularStores(category);
        return ResponseEntity.ok(popularStores);
    }

    @GetMapping("/{userid}/frequent")
    public ResponseEntity<List<Store>> getFrequentStores(@PathVariable Long userid) {
        List<Store> storeNameByUserId = storeRepository.findStoreNameByUserId(userid);
        return ResponseEntity.ok(storeNameByUserId);
    }

}
