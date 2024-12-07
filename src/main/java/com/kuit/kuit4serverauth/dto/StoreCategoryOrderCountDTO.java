package com.kuit.kuit4serverauth.dto;

import com.kuit.kuit4serverauth.model.Category;
import com.kuit.kuit4serverauth.model.Store;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreCategoryOrderCountDTO {

    Store store;
    Category category;
    int orderCount;

}
