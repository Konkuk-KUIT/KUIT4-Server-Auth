package com.kuit.kuit4serverauth.domain.order.repository;

import com.kuit.kuit4serverauth.domain.order.model.dao.Order;
import com.kuit.kuit4serverauth.domain.store.model.dao.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Order> searchByUserId(Long userId) {
        String sql = "SELECT " +
                "s.name AS store_name, " +
                "o.order_price AS order_price, " +
                "o.discount AS order_discount, " +
                "oli.name AS order_item_name, " +
                "o.order_number AS order_number, " +
                "o.created_date AS ordered_date, " +
//                "o.delivery_method AS delivery_method, " +
                "o.requirement AS order_requirement, " +
                "oli.amount AS item_amount, " +
                "oog.name AS option_group_name, " +
                "opt.name AS option_name " +
                "FROM Orders o LEFT JOIN OrderLineItems oli ON o.order_id=oli.order_id " +
                "LEFT JOIN OrderOptionGroups oog ON oli.orderLineItem_id=oog.orderLineItem_id " +
                "LEFT JOIN OrderOptions opt ON oog.orderOptionGroup_id=opt.orderOptionGroup_id " +
                "JOIN Stores s ON o.store_id=s.store_id " +
                "WHERE o.user_id = ? " +
                "ORDER BY o.order_price ASC, " +
                "o.order_id, oli.orderLineItem_id, oog.optionGroup_id, opt.option_id ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class), userId);
    }
}
