package com.kuit.kuit4serverauth.domain.store.repository;

import com.kuit.kuit4serverauth.domain.store.model.dao.FirstStore;
import com.kuit.kuit4serverauth.domain.store.model.dao.Store;
import com.kuit.kuit4serverauth.domain.store.model.dao.StoreMenu;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class StoreRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Store> findByMinDeliveryPrice(int minDeliveryPrice) {
        String sql = "SELECT s.store_id, s.name, s.address, s.category, s.phone_number AS phoneNum, s.minDeliveryPrice " +
                "FROM Stores s " +
                "WHERE s.minDeliveryPrice >= ? AND s.status = 'active'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Store.class), minDeliveryPrice);
    }

    public List<StoreMenu> findByMenu(String menu) {
        String sql = "SELECT s.name AS storeName, m.name AS menuName " +
                "FROM Stores s JOIN Menus m ON s.store_id=m.store_id " +
                "WHERE m.name LIKE ? AND s.status = 'active'";

        log.info("Original menu value: {}", menu);
        String keyword = "%" + menu + "%";
        log.info("Generated keyword: {}", keyword);

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StoreMenu.class), keyword);
    }


    public List<FirstStore> findByCategory(String category) {
        String sql = "SELECT s.name AS name, s.category AS category, COUNT(o.order_id) AS orderCounts " +
                "FROM Stores s JOIN Orders o ON s.store_id=o.store_id " +
                "WHERE s.category=? " +
                "GROUP BY s.store_id ORDER BY COUNT(o.order_id) DESC LIMIT 1";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(FirstStore.class), category);
    }

    public List<Store> searchForStore(Long userId) {
        String sql = "SELECT DISTINCT s.store_id, s.name, s.address, s.category, s.phone_number AS phoneNum, s.minDeliveryPrice " +
                "FROM Stores s JOIN Orders o ON s.store_id=o.store_id " +
                "WHERE o.user_id = ? " +
                "GROUP BY o.store_id HAVING COUNT(o.order_id) >= 2";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Store.class), userId);
    }
}
