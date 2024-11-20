package com.kuit.kuit4serverauth.repository;

import com.kuit.kuit4serverauth.model.User;
import com.kuit.kuit4serverauth.model.dao.RefreshToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Slf4j
public class RefreshTokenRepository {
    private final JdbcTemplate jdbcTemplate;

    public RefreshTokenRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RefreshToken findByUsername(String username) {
        String sql = "SELECT * FROM refresh_tokens WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(RefreshToken.class), username);
    }

    public void save(RefreshToken refreshToken) {
        String sql = "INSERT INTO refresh_tokens (username, refresh_token, expires_at) VALUES (?, ?, ?)";
        log.info("저장"+refreshToken.getRefreshToken());
        jdbcTemplate.update(sql, refreshToken.getUsername(), refreshToken.getRefreshToken(), refreshToken.getExpiresAt());
    }
}
