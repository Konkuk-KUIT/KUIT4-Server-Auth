package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.model.Restaurant;
import com.kuit.kuit4serverauth.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class RestaurantRepository {

    private final JdbcTemplate jdbcTemplate;

    public RestaurantRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Restaurant> findByLeastPriceAndStatus(String leastPrice) {
        String sql = """
        SELECT *
        FROM restaurants
        WHERE leastPrice <= ?
          AND status = 'ACTIVE'
    """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Restaurant.class), leastPrice);
    }


    public List<Map<String, Object>> findMenusAndRestaurantsByMenuName(String menu) {
        String sql = """
    SELECT r.restaurant_id AS restaurantId, 
           r.restaurant_name AS restaurantName, 
           r.least_delivery_price AS leastDeliveryPrice, 
           r.status AS status,
           r.category AS category,
           m.menu_id AS menuId,
           m.name AS menuName
    FROM restaurants r
    JOIN menus m ON r.restaurant_id = m.restaurant_id
    WHERE m.name LIKE CONCAT('%', ?, '%')
""";
        return jdbcTemplate.queryForList(sql, menu);
    }

}
