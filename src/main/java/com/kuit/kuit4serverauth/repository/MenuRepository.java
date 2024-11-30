package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.DTO.Response.MenuSearchResponse;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuRepository {
    private final JdbcTemplate jdbcTemplate;

    public MenuRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 최소 주문 금액과 상태로 가게 필터링
    public List<MenuSearchResponse> findStoresAndMenuByKeyword(String keyword) {
        String sql = "SELECT store.name AS store_name, menu.name AS menu_name FROM Menu menu JOIN Store store ON store.store_id = menu.store_id WHERE menu.name LIKE '%돈%'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MenuSearchResponse.class), keyword);
    }
}
