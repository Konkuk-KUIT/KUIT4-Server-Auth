package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.model.Store;
import com.kuit.kuit4serverauth.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/stores")
    public ResponseEntity<List<Store>> getStores(@RequestParam("minimumOrderPrice") Integer minimumOrderPrice,
                                                 @RequestParam("status") String status) {
        List<Store> stores = storeService.findStoresByConditions(minimumOrderPrice, status);

        return ResponseEntity.ok(stores);
    }
}
