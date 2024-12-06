package com.kuit.kuit4serverauth.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MenuRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findMenuAndStoresByKeyword(String keyword) {
        String sql = "SELECT " +
                "   m.menu_id AS menuId, " +
                "   m.name AS menuName, " +
                "   s.store_id AS storeId, " +
                "   s.name AS storeName, " +
                "   s.status AS storeStatus " +
                "FROM menu m " +
                "JOIN store s ON m.store_id = s.store_id " +
                "WHERE m.name LIKE ? AND s.status = 'active'";

        return jdbcTemplate.query(sql, new Object[]{"%" + keyword + "%"}, (rs, rowNum) -> Map.of(
                "menuId", rs.getLong("menuId"),
                "menuName", rs.getString("menuName"),
                "storeId", rs.getLong("storeId"),
                "storeName", rs.getString("storeName"),
                "storeStatus", rs.getString("storeStatus")
        ));
    }
}
