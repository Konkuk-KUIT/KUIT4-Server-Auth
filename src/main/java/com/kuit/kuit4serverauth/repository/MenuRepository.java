package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.dto.MenuAndStore;
import com.kuit.kuit4serverauth.model.Menu;
import com.kuit.kuit4serverauth.model.Store;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuRepository {
    private final JdbcTemplate jdbcTemplate;

    public MenuRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MenuAndStore> findMenuByName(String name) {
        String query = "SELECT s.storename, m.menuName\n" +
                "FROM store s JOIN menu m on s.storeID = m.storeID\n" +
                "where m.menuName like ?";
        return jdbcTemplate.query(query, new Object[]{"%" + name + "%"}, (rs, rowNum) -> new MenuAndStore(
                Menu.builder()
                        .menuId(rs.getLong("menuId"))
                        .menuName(rs.getString("menuName"))
                        .build(),
                Store.builder()
                        .storeId(rs.getLong("storeId"))
                        .storeName(rs.getString("storeName"))
                        .build()
        ));
    }
}
