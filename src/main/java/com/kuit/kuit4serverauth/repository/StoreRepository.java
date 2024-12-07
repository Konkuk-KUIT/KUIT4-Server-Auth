package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.model.Store;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StoreRepository {

    private final JdbcTemplate jdbcTemplate;

    public StoreRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 최소 주문 금액 조건과 활성 상태(status) 조건을 만족하는 음식점 조회.
    public List<Store> findByMinimumOrderPriceAndStatus(Integer minimumOrderPrice, String status){
        String sql = "SELECT * FROM stores s WHERE s.minimum_order_price >= ? AND s.status = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Store.class), minimumOrderPrice, status);
    }


}
