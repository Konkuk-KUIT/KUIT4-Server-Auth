package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.DTO.Response.Menu.MenuOptionResponse;
import com.kuit.kuit4serverauth.DTO.Response.Menu.MenuResponse;
import com.kuit.kuit4serverauth.DTO.Response.OrderResponse;
import com.kuit.kuit4serverauth.DTO.Response.Store.FrequentStoreResponse;
import com.kuit.kuit4serverauth.DTO.Response.Store.PopularCategortStoreResponse;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
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
                "    (order.price + IFNULL(menu_option.price, 0)) AS total_price,\n" +
                "    menu.menu_id,\n" +
                "    menu.name AS menu_name,\n" +
                "    menu_option.menu_option_id,\n" +
                "    menu_option.essential_option,\n" +
                "    menu_option.select_option,\n" +
                "    menu_option.price AS option_price\n" +
                "FROM Order order\n" +
                "JOIN Menu menu ON menu.menu_id = order.menu_id\n" +
                "LEFT JOIN menu_option menu_option ON menu_option.menu_option_id = order.menu_option_id\n" +
                "WHERE order.user_id = ?\n" +
                "ORDER BY total_price ASC;\n";

        // 커스텀 RowMapper로 변환
        return jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<OrderResponse>() {
            @Override
            public OrderResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                OrderResponse orderResponse = new OrderResponse();
                orderResponse.setOrder_id(rs.getLong("order_id"));
                orderResponse.setPayment(rs.getString("payment"));
                orderResponse.setTotal_price(rs.getInt("total_price"));

                // 메뉴 정보를 생성
                MenuResponse menuResponse = new MenuResponse();
                menuResponse.setMenu_id(rs.getLong("menu_id"));
                menuResponse.setMenu_name(rs.getString("menu_name"));

                // 메뉴 옵션 추가
                MenuOptionResponse menuOptionResponse = new MenuOptionResponse();
                menuOptionResponse.setMenu_option_id(rs.getLong("menu_option_id"));
                menuOptionResponse.setEssential_option(rs.getString("essential_option"));
                menuOptionResponse.setSelect_option(rs.getString("select_option"));
                menuOptionResponse.setPrice(rs.getInt("option_price"));

                menuResponse.setMenu_option(Arrays.asList(menuOptionResponse));
                orderResponse.setMenu_list(Arrays.asList(menuResponse));

                return orderResponse;
            }
        });
    }
}
