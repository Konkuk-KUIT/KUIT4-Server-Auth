DROP TABLE IF EXISTS refreshTokens;
CREATE TABLE refreshTokens(
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(50) NOT NULL,
                    refreshToken VARCHAR(500) NOT NULL
);