package com.kuit.kuit4serverauth.controller;

import com.kuit.kuit4serverauth.dto.StoreCategoryOrderCountDTO;
import com.kuit.kuit4serverauth.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories/{category_id}/most-ordered-store")
    public ResponseEntity<List<StoreCategoryOrderCountDTO>> getMostOrderedStoreByCategory(@PathVariable("category_id") Long category_id) {
        List<StoreCategoryOrderCountDTO> mostOrderedStore = categoryService.findMostOrderedStoreByCategory(category_id);
        return ResponseEntity.ok(mostOrderedStore);
    }
}
