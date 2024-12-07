CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       role VARCHAR(20) NOT NULL
);

CREATE TABLE refreshTokens (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    refreshToken VARCHAR(255) NOT NULL
);

CREATE TABLE categories (
    category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(50) NOT NULL
);

CREATE TABLE stores (
    store_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    minimum_order_price INTEGER,
    status VARCHAR(50) NOT NULL,
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

CREATE TABLE menus (
    menu_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price INT default 0,
    store_id BIGINT,
    FOREIGN KEY (store_id) REFERENCES stores(store_id)
);

CREATE TABLE orders (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    total_price INT default 0,
    store_id BIGINT,
    menu_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (store_id) REFERENCES stores(store_id),
    FOREIGN KEY (menu_id) REFERENCES menus(menu_id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

