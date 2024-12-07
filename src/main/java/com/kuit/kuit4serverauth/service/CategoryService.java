package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.dto.StoreCategoryOrderCountDTO;
import com.kuit.kuit4serverauth.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<StoreCategoryOrderCountDTO> findMostOrderedStoreByCategory(Long category_id) {
        return categoryRepository.findMostOrderedStoreByCategory(category_id);
    }

}
