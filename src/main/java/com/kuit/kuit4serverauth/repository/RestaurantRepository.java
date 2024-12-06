package com.kuit.kuit4serverauth.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RestaurantRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findStoresByConditions(int minPrice, String status) {
        String sql = "SELECT " +
                "   store_id AS storeId, " +
                "   name, " +
                "   min_price AS minPrice, " +
                "   status " +
                "FROM stores " +
                "WHERE min_price >= ? AND status = ?";

        return jdbcTemplate.query(sql, new Object[]{minPrice, status}, (rs, rowNum) -> Map.of(
                "storeId", rs.getLong("storeId"),
                "name", rs.getString("name"),
                "minPrice", rs.getInt("minPrice"),
                "status", rs.getString("status")
        ));
    }
}
