package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.dto.FrequentlyOrderedStore;
import com.kuit.kuit4serverauth.dto.PopularStore;
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
        String query = "SELECT storeID, storename FROM Store WHERE minOrderPrice >= ? AND status = 'active'";
        return jdbcTemplate.query(query, new Object[]{minOrderPrice}, (rs, rowNum) -> Store.builder()
                .storeID(rs.getLong("storeId"))
                .storeName(rs.getString("storeName"))
                .build());
    }

    public List<PopularStore> findPopularStores(String category){
        String query = """
                SELECT s.storeID,s.storeName, count(o.orderID) as ordercount
                FROM store s join `order` o on s.storeID = o.storeID
                WHERE s.category = ?
                GROUP BY s.storeID
                ORDER BY ordercount DESC
                LIMIT 5""";
        return jdbcTemplate.query(query, new Object[]{category}, (rs, rowNum) -> new PopularStore(
                rs.getString("storename"),
                rs.getInt("ordercount")
        ));
    }

    public List<FrequentlyOrderedStore> findStoreNameByUserId(Long userId){
        String query = """
                SELECT DISTINCT s.storeID, s.storename
                FROM `order` o
                JOIN store s on o.storeID = s.storeID
                WHERE o.userID = ?
                GROUP BY s.storeID,s.storename
                HAVING count(o.orderID) >= 2""";
        return jdbcTemplate.query(query, new Object[]{userId}, (rs, rowNum) -> new FrequentlyOrderedStore(
                rs.getString("storename")
                )
        );
    }
}
