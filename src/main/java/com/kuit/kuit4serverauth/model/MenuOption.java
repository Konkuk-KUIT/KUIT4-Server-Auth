package com.kuit.kuit4serverauth.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuOption {
    private long menuOptionId;
    private long menuId;
    private String name;
    private boolean required;
    private boolean multi;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
}
