package com.kuit.kuit4serverauth.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class MenuOption {
    private Long menuOptionId;
    private String option;
    private String content;
    private int price;
    private Timestamp created_at;
    private Timestamp updated_at;
    private String status;
}
