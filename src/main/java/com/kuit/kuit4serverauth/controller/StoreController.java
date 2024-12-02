package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.model.Store;
import com.kuit.kuit4serverauth.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping("/min-order")
    public List<Store> getStoresByMinOrder(@RequestParam("minOrder") int minOrder, @RequestParam("status") String status){
        return storeService.getStoresFilteredByMinOrder(minOrder, status);
    }

    @GetMapping("/stores/category-top")
    public Store getTopOrderedStoreForCategory(@RequestParam("category") String category){
        return storeService.findTopStoreByCategory(category);
    }

    @GetMapping("/stores/user/{userId}/multiple-ordered")
    public List<Store> findStoresUserMultipleOrdered(@PathVariable("userId") long userId){
        return storeService.findStoresUserMultipleOrdered(userId);
    }
}
