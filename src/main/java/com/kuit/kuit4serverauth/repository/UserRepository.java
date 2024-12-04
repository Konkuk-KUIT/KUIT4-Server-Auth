package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.dto.TotalOrderInfo;
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

    public List<TotalOrderInfo> getTotalOrderedInfo(long userId) {
        String sql = "SELECT o.*, od.*, m.*, mo.*, mods.* " +
                "FROM orders o " +
                "LEFT JOIN order_details od ON o.order_id = od.order_id " +
                "LEFT JOIN menus m ON od.menu_id = m.menu_id " +
                "LEFT JOIN menu_options mo ON m.menu_id = mo.menu_id " +
                "LEFT JOIN menu_option_details mods ON mo.menu_option_id = mods.menu_option_id " +
                "WHERE o.user_id = ? " +
                "ORDER BY o.price DESC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            // Order 객체 생성
            Order order = Order.builder()
                    .orderId(rs.getLong("o.order_id"))
                    .userId(rs.getLong("o.user_id"))
                    .storeId(rs.getLong("o.store_id"))
                    .price(rs.getLong("o.price"))
                    .createdAt(rs.getTimestamp("o.created_at") != null ? rs.getTimestamp("o.created_at").toLocalDateTime() : null)
                    .updatedAt(rs.getTimestamp("o.updated_at") != null ? rs.getTimestamp("o.updated_at").toLocalDateTime() : null)
                    .status(rs.getString("o.status"))
                    .build();

            // OrderDetail 객체 생성
            OrderDetail orderDetail = OrderDetail.builder()
                    .orderDetailId(rs.getLong("od.order_detail_id"))
                    .orderId(rs.getLong("od.order_id"))
                    .menuId(rs.getLong("od.menu_id"))
                    .quantity(rs.getLong("od.quantity"))
                    .createdAt(rs.getTimestamp("od.created_at") != null ? rs.getTimestamp("od.created_at").toLocalDateTime() : null)
                    .updatedAt(rs.getTimestamp("od.updated_at") != null ? rs.getTimestamp("od.updated_at").toLocalDateTime() : null)
                    .status(rs.getString("od.status"))
                    .build();

            // Menu 객체 생성
            Menu menu = Menu.builder()
                    .menuId(rs.getLong("m.menu_id"))
                    .storeId(rs.getLong("m.store_id"))
                    .name(rs.getString("m.name"))
                    .price(rs.getLong("m.price"))
                    .createdAt(rs.getTimestamp("m.created_at") != null ? rs.getTimestamp("m.created_at").toLocalDateTime() : null)
                    .updatedAt(rs.getTimestamp("m.updated_at") != null ? rs.getTimestamp("m.updated_at").toLocalDateTime() : null)
                    .status(rs.getString("m.status"))
                    .build();

            // MenuOption 객체 생성
            MenuOption menuOption = MenuOption.builder()
                    .menuOptionId(rs.getLong("mo.menu_option_id"))
                    .menuId(rs.getLong("mo.menu_id"))
                    .name(rs.getString("mo.name"))
                    .required(rs.getBoolean("mo.required"))
                    .multi(rs.getBoolean("mo.multi"))
                    .createdAt(rs.getTimestamp("mo.created_at") != null ? rs.getTimestamp("mo.created_at").toLocalDateTime() : null)
                    .updatedAt(rs.getTimestamp("mo.updated_at") != null ? rs.getTimestamp("mo.updated_at").toLocalDateTime() : null)
                    .status(rs.getString("mo.status"))
                    .build();

            // MenuOptionDetail 객체 생성
            MenuOptionDetail menuOptionDetail = MenuOptionDetail.builder()
                    .menuOptionDetailId(rs.getLong("mods.menu_option_detail_id"))
                    .menuOptionId(rs.getLong("mods.menu_option_id"))
                    .name(rs.getString("mods.name"))
                    .additionalPrice(rs.getLong("mods.additional_price"))
                    .createdAt(rs.getTimestamp("mods.created_at") != null ? rs.getTimestamp("mods.created_at").toLocalDateTime() : null)
                    .updatedAt(rs.getTimestamp("mods.updated_at") != null ? rs.getTimestamp("mods.updated_at").toLocalDateTime() : null)
                    .status(rs.getString("mods.status"))
                    .build();

            // TotalOrderInfo 객체 생성 후 반환
            return new TotalOrderInfo(order, orderDetail, menu, menuOption, menuOptionDetail);
        }, userId);
    }

}
