package com.kuit.kuit4serverauth.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuOptionDetail {
    private long menuOptionDetailId;
    private long menuOptionId;
    private String name;
    private long additionalPrice;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
}
