package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StoreRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StoreRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Store store) {
        String sql = "INSERT INTO stores (name, category, min_order, telephone, address, latitude, longitude) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                store.getName(),
                store.getCategory(),
                store.getMinOrder(),
                store.getTelephone(),
                store.getAddress(),
                store.getLatitude(),
                store.getLongitude());
    }

    public Store findById(int storeId){
        String sql = "SELECT * FROM stores WHERE store_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Store.class), storeId);
    }

    public List<Store> filterByMinOrder(int minOrder, String status) {
        String sql = "SELECT * FROM stores WHERE min_order >= ? AND status = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Store.class), minOrder, status);
    }

    public Store findTopOrderedByCategory(String category) {
        String sql = "SELECT s.*, COUNT(o.order_id) AS order_count " +
                "FROM stores s " +
                "JOIN orders o ON s.store_id = o.store_id " +
                "WHERE s.category = ? " +
                "GROUP BY s.store_id " +
                "ORDER BY order_count DESC " +
                "LIMIT 1";

        return jdbcTemplate.queryForObject(sql, new Object[]{category}, (rs, rowNum) -> {
            return Store.builder()
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
        });
    }

    public List<Store> findUserMultipleOrdered(long userId) {
        String sql = "SELECT DISTINCT s.* " +
                "FROM stores s " +
                "JOIN orders o ON s.store_id = o.store_id " +
                "WHERE o.user_id = ? " +
                "GROUP BY s.store_id " +
                "HAVING COUNT(o.order_id) >= 2";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return Store.builder()
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
        }, userId);
    }

    public List<Store> findUserMultipleOrderedPaging(long userId, long lastStoreId, int pageNum) {
        String sql = "SELECT DISTINCT s.* " +
                "FROM stores s " +
                "JOIN orders o ON s.store_id = o.store_id " +
                "WHERE o.user_id = ? AND o.store_id > ? " +
                "GROUP BY s.store_id " +
                "HAVING COUNT(o.order_id) >= 2 "+
                "LIMIT ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return Store.builder()
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
        }, userId, lastStoreId, pageNum);
    }
}
