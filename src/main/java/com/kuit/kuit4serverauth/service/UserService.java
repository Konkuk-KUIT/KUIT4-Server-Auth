package com.kuit.kuit4serverauth.service;

import com.kuit.kuit4serverauth.dto.TotalOrderInfo;
import com.kuit.kuit4serverauth.dto.TotalOrderInfoListAndPrice;
import com.kuit.kuit4serverauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public TotalOrderInfoListAndPrice getTotalUserOrderedInfo(long userId){
        List<TotalOrderInfo> TotalOrderInfoList = userRepository.getTotalOrderedInfo(userId);
        long totalPrice = 0;
        for(TotalOrderInfo totalOrderInfo: TotalOrderInfoList){
            totalPrice += totalOrderInfo.getOrder().getPrice();
        }
        return new TotalOrderInfoListAndPrice(totalPrice, TotalOrderInfoList);
    }
}
