package com.kuit.kuit4serverauth.DTO.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberOrderDetailResponse {
    private Long orderId;
    private String menuName;
    private double totalPrice;
    private String options;
}
