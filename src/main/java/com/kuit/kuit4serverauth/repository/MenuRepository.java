package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.dto.MenuAndStore;
import com.kuit.kuit4serverauth.model.Menu;
import com.kuit.kuit4serverauth.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MenuRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MenuAndStore> findMenusAndStoresByKeyword(String keyword) {
        String sql = "SELECT * " +
                "FROM menus m " +
                "JOIN stores s ON m.store_id = s.store_id " +
                "WHERE m.name LIKE CONCAT('%', ?, '%')";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Menu menu = Menu.builder()
                    .menuId(rs.getLong("m.menu_id"))
                    .menuId(rs.getLong("m.store_id"))
                    .name(rs.getString("m.name"))
                    .price(rs.getLong("m.price"))
                    .createdAt(rs.getTimestamp("m.created_at").toLocalDateTime())
                    .updatedAt(rs.getTimestamp("m.updated_at").toLocalDateTime())
                    .status(rs.getString("m.status"))
                    .build();

            Store store = Store.builder()
                    .storeId(rs.getLong("s.store_id"))
                    .name(rs.getString("s.name"))
                    .category(rs.getString("s.category"))
                    .minOrder(rs.getLong("s.min_order"))
                    .telephone(rs.getString("s.telephone"))
                    .address(rs.getString("s.address"))
                    .latitude(rs.getDouble("s.latitude"))
                    .longitude(rs.getDouble("s.longitude"))
                    .createdAt(rs.getTimestamp("s.created_at").toLocalDateTime())
                    .updatedAt(rs.getTimestamp("s.updated_at").toLocalDateTime())
                    .status(rs.getString("s.status"))
                    .build();

            return new MenuAndStore(menu, store);
        }, keyword);
    }
}
