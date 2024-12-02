package com.kuit.kuit4serverauth.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    private long storeId;
    private String name;
    private String category;
    private long minOrder;
    private String telephone;
    private String address;
    private double latitude;
    private double longitude;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
}
