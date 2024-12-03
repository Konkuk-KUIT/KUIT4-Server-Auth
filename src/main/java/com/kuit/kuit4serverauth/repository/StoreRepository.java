package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.model.Store;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class StoreRepository {
    private final JdbcTemplate jdbcTemplate;

    public StoreRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Store> findByMinOrderPrice(int minOrderPrice) {
        String query = "SELECT * FROM Store WHERE minOrderPrice >= ? AND status = 'active'";
        return jdbcTemplate.query(query, new Object[]{minOrderPrice}, (rs, rowNum) -> Store.builder()
                .storeId(rs.getLong("storeId"))
                .storeName(rs.getString("storeName"))
                .build());
    }

    public List<Store> findPopularStores(String category){
        String query = "SELECT s.storeName, count(o.orderID) as COUNT " +
                "FROM store s join order o on s.storeID = o.storeID" +
                "WHERE s.category = ?" +
                "GROUP BY s.storeID" +
                "ORDER BY COUNT DESC" +
                "LIMIT 5";
        return jdbcTemplate.query(query, new Object[]{category}, (rs, rowNum) -> Store.builder()
                .storeId(rs.getLong("storeId"))
                .storeName(rs.getString("storename"))
                .build());
    }

    public List<Store> findStoreNameByUserId(Long userId){
        String query = "SELECT DISTINCT s.storename" +
                "FROM order o JOIN store s on o.storeID = s.storeID" +
                "WHERE o.userID = ?" +
                "GROUP BY s.storeID" +
                "HAVING count(o.orderID) >= 2";
        return jdbcTemplate.query(query, new Object[]{userId}, (rs, rowNum) -> Store.builder()
                .storeId(rs.getLong("storeID"))
                .storeName(rs.getString("storename"))
                .build()
        );
    }
}
