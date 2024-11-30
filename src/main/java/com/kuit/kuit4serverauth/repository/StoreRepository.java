package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.DTO.Response.StoreResponse;
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

    // 최소 주문 금액과 상태로 가게 필터링
    public List<StoreResponse> findStoresByMinPriceAndStatus(int minPrice, String status) {
        String sql = "SELECT store.store_id, store.name, store.minimun_delivery_price, store.status  FROM Store store WHERE minimum_delivery_price >= ? AND status = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StoreResponse.class), minPrice, status);
    }
}
