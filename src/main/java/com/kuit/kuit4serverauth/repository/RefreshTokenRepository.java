package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RefreshTokenRepository {

    private final JdbcTemplate jdbcTemplate;

    public RefreshTokenRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String findByUsername(String username) {
        String sql = "SELECT refreshToken FROM refreshTokens WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> rs.getString("refreshToken"), username);
    }

    public void save(String username, String refreshToken) {
        String sql = "INSERT INTO refreshTokens (username, refreshToken) VALUES (?, ?)";
        jdbcTemplate.update(sql, username, refreshToken);
    }

    public void updateRefreshToken(String username, String refreshToken) {
        String sql = "UPDATE refreshTokens SET refreshToken = ? WHERE username = ?";
        jdbcTemplate.update(sql, refreshToken, username);
    }
}
