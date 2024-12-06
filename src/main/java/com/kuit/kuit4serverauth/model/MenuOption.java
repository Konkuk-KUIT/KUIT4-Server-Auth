package com.kuit.kuit4serverauth.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class MenuOption {
    private Long menuOptionID;
    private String option;
    private int price;
}
