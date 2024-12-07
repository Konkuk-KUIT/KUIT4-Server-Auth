package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.dto.MenuStoreDTO;
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

    // 메뉴 이름에 특정 문자열이 포함된 메뉴와 해당 음식점을 조회.
    public List<MenuStoreDTO> findMenuAndStoreNameByMenuName(String menuName){
        String sql = "SELECT s.store_id, s.name, s.minimum_order_price, s.status, s.category_id, m.menu_id, m.name AS menuName, m.price, m.store_id " +
                "FROM menus m " +
                "JOIN stores s ON s.store_id = m.store_id " +
                "JOIN categories c ON s.category_id = c.category_id " +
                "WHERE m.name LIKE ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Store store = Store.builder()
                    .store_id(rs.getLong("store_id"))
                    .name(rs.getString("name"))
                    .category_id(rs.getLong("category_id"))
                    .minimum_order_price(rs.getInt("minimum_order_price"))
                    .status(rs.getString("status"))
                    .build();

            Menu menu = Menu.builder()
                    .menu_id(rs.getLong("menu_id"))
                    .name(rs.getString("menuName"))
                    .price(rs.getInt("price"))
                    .store_id(rs.getLong("store_id"))
                    .build();

            return MenuStoreDTO.builder()
                    .store(store)
                    .menu(menu)
                    .build();
        }, "%"+menuName+"%");
    }
}
