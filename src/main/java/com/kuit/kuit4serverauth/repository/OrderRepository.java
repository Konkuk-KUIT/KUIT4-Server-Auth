package com.kuit.kuit4serverauth.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<String> findDistinctStoresByMemberId(Long memberId) {
        String sql = "select dintict s.name AS storeName " +
                "from orders o " +
                "join stores s ON o.store_id = s.store_id " +
                "where o.member_id = ?";

        return jdbcTemplate.query(sql, new Object[]{memberId}, (rs, rowNum) -> rs.getString("storeName"));
    }
}
