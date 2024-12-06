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
        String query = "select o.orderID, o.total_price, m.menuID, m.menuName, mo.menuOptionID, mo.option\n" +
                "from `Order` o " +
                "join menu m on o.menuID = m.menuID " +
                "join MenuOption mo on m.menuID = mo.menuID\n" +
                "where o.userID = ?";
        return jdbcTemplate.query(query, new Object[]{userId},(rs, rowNum) -> new OrderDetail(
                rs.getLong("orderID"),
                rs.getInt("total_price"),
                rs.getLong("menuID"),
                rs.getString("menuName"),
                rs.getLong("menuOptionID"),
                rs.getString("option")

        ));
    }


}
