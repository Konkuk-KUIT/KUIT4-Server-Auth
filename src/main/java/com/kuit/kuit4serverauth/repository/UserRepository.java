package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.dto.OrderInfoDTO;
import com.kuit.kuit4serverauth.model.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
    }

    // 특정 회원이 두 번 이상 주문한 음식점 조회 (중복 제거).
    public List<Store> findStoresWithMultipleOrders(Long userId, int minOrderCount) {
        String sql = "SELECT distinct s.store_id, s.name, s.category_id, s.minimum_order_price, s.status " +
                "FROM stores s JOIN orders o ON s.store_id = o.store_id " +
                "WHERE o.user_id = ? " +
                "GROUP BY s.store_id " +
                "HAVING COUNT(o.user_id) >= ?";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Store.class), userId, minOrderCount);
    }

    public List<OrderInfoDTO> findUsersAllOrders(Long userId) {
        String sql = "SELECT o.order_id, o.total_price , o.store_id, o.user_id, SUM(o.total_price) AS total_sum, m.menu_id, " +
                "s.name AS storeName, " +
                "m.name AS menuName " +
                "FROM orders o " +
                "JOIN users u ON o.user_id = u.id " +
                "JOIN stores s ON o.store_id = s.store_id " +
                "JOIN menus m ON o.menu_id = m.menu_id " +
                "WHERE o.user_id = ? " +
                "GROUP BY o.order_id, o.total_price, o.store_id, o.user_id, m.menu_id, s.name, m.name " +
                "ORDER BY o.total_price DESC";


        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> {
            Order order = Order.builder()
                    .order_id(rs.getLong("order_id"))
                    .total_price(rs.getInt("total_price"))
                    .store_id(rs.getLong("store_id"))
                    .menu_id(rs.getLong("menu_id"))
                    .user_id(rs.getLong("user_id"))
                    .build();

            OrderInfoDTO dto = OrderInfoDTO.builder()
                    .order(order)
                    .totalPrice(rs.getInt("total_sum"))
                    .storeName(rs.getString("storeName"))
                    .menuName(rs.getString("menuName"))
                    .build();
            return dto; // Return the DTO
        });
    }



}
