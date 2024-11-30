package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.DTO.Response.OrderResponse;
import com.kuit.kuit4serverauth.DTO.Response.Store.FrequentStoreResponse;
import com.kuit.kuit4serverauth.DTO.Response.Store.PopularCategortStoreResponse;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FrequentStoreResponse> findFrequentStoresByUserId(Long userId) {
        String sql = "SELECT DISTINCT store.store_id, store.name FROM orders o JOIN stores store ON store.store_id = o.store_id WHERE o.user_id = ? GROUP BY store.store_id, store.name HAVING COUNT(o.order_id) > 1 ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(FrequentStoreResponse.class), userId);
    }

    public List<PopularCategortStoreResponse> findPopularStoresByCategory(String category, int limit) {
        String sql = "SELECT store.store_id, store.name, COUNT(order.order_id)\n" +
                "FROM Order order\n" +
                "JOIN Store store ON store.store_id = order.store_id\n" +
                "JOIN Menu menu ON menu.menu_id = order.menu_id\n" +
                "WHERE menu.category = ?\n" +
                "GROUP BY store.name\n" +
                "ORDER BY order_count DESC\n" +
                "LIMIT ?;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PopularCategortStoreResponse.class), category, limit);
    }

    public List<OrderResponse> findOrdersByUserId(Long userId) {
        String sql = "SELECT \n" +
                "    order.order_id,\n" +
                "    order.payment,\n" +
                "    order.price AS total_price,\n" +
                "    menu.menu_id,\n" +
                "    menu.name AS menu_name,\n" +
                "    menu_option.menu_option_id,\n" +
                "    menu_option.essential_option,\n" +
                "    menu_option.select_option,\n" +
                "    menu_option.price\n" +
                "FROM Order order\n" +
                "JOIN Menu menu ON menu.menu_id = order.menu_id\n" +
                "LEFT JOIN menu_option menu_option ON menu_option.menu_option_id = order.menu_option_id\n" +
                "WHERE order.user_id = ?\n" +
                "ORDER BY order.order_id, menu.menu_id, menu_option.menu_option_id;\n";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderResponse.class), userId);
    }
}
