package com.kuit.kuit4serverauth.dto;

import com.kuit.kuit4serverauth.model.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TotalOrderInfo {
    private final Order order;
    private final OrderDetail orderDetail;
    private final Menu menu;
    private final MenuOption menuOption;
    private final MenuOptionDetail menuOptionDetail;
}
