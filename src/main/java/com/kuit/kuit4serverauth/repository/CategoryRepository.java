package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.dto.StoreCategoryOrderCountDTO;
import com.kuit.kuit4serverauth.model.Category;
import com.kuit.kuit4serverauth.model.Store;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 특정 카테고리에서 주문이 가장 많은 상위 음식점 조회.
    public List<StoreCategoryOrderCountDTO> findMostOrderedStoreByCategory(Long category_id) {
        String sql = "SELECT s.store_id, s.name, s.category_id, s.minimum_order_price, s.status, COUNT(o.order_id) AS orderCount, c.category_id, c.category_name " +
                "FROM stores s " +
                "JOIN orders o on s.store_id = o.store_id " +
                "JOIN categories c on s.category_id = c.category_id " +
                "WHERE s.category_id = ? " +
                "GROUP BY s.store_id, s.name, s.category_id, s.minimum_order_price, s.status, c.category_id, c.category_name " +
                "ORDER BY orderCount DESC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Store store = Store.builder()
                    .store_id(rs.getLong("store_id"))
                    .name(rs.getString("name"))
                    .category_id(rs.getLong("category_id"))
                    .minimum_order_price(rs.getInt("minimum_order_price"))
                    .status(rs.getString("status"))
                    .build();

            Category category = Category.builder()
                    .category_id(rs.getLong("category_id"))
                    .category_name(rs.getString("category_name"))
                    .build();

            return StoreCategoryOrderCountDTO.builder()
                    .store(store)
                    .category(category)
                    .orderCount(rs.getInt("orderCount"))
                    .build();
        }, category_id);
    }
}
