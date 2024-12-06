package com.kuit.kuit4serverauth.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class Menu {
    private Long menuId;
    private String menuName;
    private String category;
}
