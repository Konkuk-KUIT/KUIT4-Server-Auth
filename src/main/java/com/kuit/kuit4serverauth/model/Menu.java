package com.kuit.kuit4serverauth.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private long menuId;
    private long storeId;
    private String name;
    private long price;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
}
