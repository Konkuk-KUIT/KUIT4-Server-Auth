package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.dto.OrderListResDto;
import com.kuit.kuit4serverauth.model.Restaurant;
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

    public List<Restaurant> findTwiceOrderedRestaurant(Long id) {
        String sql = """
        SELECT DISTINCT r.*
        FROM restaurants r
        JOIN orders o ON r.restaurant_id = o.restaurant_id
        WHERE o.member_id = ?
        GROUP BY r.restaurant_id
        HAVING COUNT(o.order_id) >= 2
    """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Restaurant.class), id);
    }

    public List<Restaurant> findTopRestaurantsByCategory(String category, int limit) {
        String sql = """
        SELECT r.*, COUNT(o.order_id) AS order_count
        FROM restaurants r
        JOIN orders o ON r.restaurant_id = o.restaurant_id
        WHERE r.category = ?
        GROUP BY r.restaurant_id
        ORDER BY order_count DESC
        LIMIT ?
    """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Restaurant.class), category, limit);
    }

    public List<OrderListResDto> findOrdersByMemberId(Long memberId) {
        String sql = """
        SELECT 
            o.order_id AS orderId,
            o.order_date AS orderDate,
            r.restaurant_name AS restaurantName,
            m.name AS menuName,
            mo.option_name AS optionName,
            mo.option_price AS optionPrice,
            (m.price + COALESCE(SUM(mo.option_price), 0)) AS totalPrice
        FROM orders o
        JOIN restaurants r ON o.restaurant_id = r.restaurant_id
        JOIN order_items oi ON o.order_id = oi.order_id
        JOIN menus m ON oi.menu_id = m.menu_id
        LEFT JOIN menu_options mo ON oi.item_id = mo.item_id
        WHERE o.member_id = ?
        GROUP BY o.order_id, r.restaurant_name, m.name, mo.option_name, mo.option_price
        ORDER BY totalPrice ASC
    """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderListResDto.class), memberId);
    }

}
