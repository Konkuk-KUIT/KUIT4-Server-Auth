package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.dto.OrderDetail;
import com.kuit.kuit4serverauth.model.Menu;
import com.kuit.kuit4serverauth.model.MenuOption;
import com.kuit.kuit4serverauth.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<OrderDetail> findOrderByUserId(Long userId) {
        String query = "select *" +
                "from Order o " +
                "join menu m on o.menuID = m.menuID " +
                "join MenuOption mo on m.menuID = mo.menuID\n" +
                "where o.userID = ?";
        return jdbcTemplate.query(query, new Object[]{userId},(rs, rowNum) -> new OrderDetail(
                Order.builder()
                        .orderId(rs.getLong("orderID"))
                        .total_price(rs.getInt("total_price"))
                        .build(),
                Menu.builder()
                        .menuId(rs.getLong("menuID"))
                        .menuName(rs.getString("menuName"))
                        .build(),
                MenuOption.builder()
                        .menuOptionId(rs.getLong("menuOptionID"))
                        .option(rs.getString("option"))
                        .build()

        ));
    }
}
