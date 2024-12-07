INSERT INTO users (username, password, role) VALUES ('admin', 'admin123', 'ROLE_ADMIN');
INSERT INTO users (username, password, role) VALUES ('user', 'user123', 'ROLE_USER');
INSERT INTO categories (category_name) VALUES ('분식');
INSERT INTO categories (category_name) VALUES ('중식');
INSERT INTO categories (category_name) VALUES ('한식');
INSERT INTO stores (name, minimum_order_price, status, category_id) VALUES ('교촌치킨', 10000, 'activated', 1);
INSERT INTO stores (name, minimum_order_price, status, category_id) VALUES ('bbq치킨', 9000, 'activated', 1);
INSERT INTO stores (name, minimum_order_price, status, category_id) VALUES ('bhc치킨', 12000, 'deactivated', 1);
INSERT INTO stores (name, minimum_order_price, status, category_id) VALUES ('중경마라', 12000, 'deactivated', 2);
INSERT INTO stores (name, minimum_order_price, status, category_id) VALUES ('홍콩반점', 6000, 'deactivated', 2);
INSERT INTO menus (name, price, store_id) VALUES ('떡볶이1', 5000, 1);
INSERT INTO menus (name, price, store_id) VALUES ('떡볶이2', 3000, 2);
INSERT INTO menus (name, price, store_id) VALUES ('떡볶이3', 3000, 3);
INSERT INTO menus (name, price, store_id) VALUES ('짜장면', 7000, 4);
INSERT INTO menus (name, price, store_id) VALUES ('짜장면', 7000, 5);
INSERT INTO orders (total_price, store_id, menu_id, user_id) VALUES (10000, 1, 1, 1);
INSERT INTO orders (total_price, store_id, menu_id, user_id) VALUES (20000, 2, 2, 1);
INSERT INTO orders (total_price, store_id, menu_id, user_id) VALUES (30000, 3, 3, 1);
INSERT INTO orders (total_price, store_id, menu_id, user_id) VALUES (40000, 1, 1, 1);
INSERT INTO orders (total_price, store_id, menu_id, user_id) VALUES (40000, 2, 2, 2);
INSERT INTO orders (total_price, store_id, menu_id, user_id) VALUES (40000, 2, 2, 2);
INSERT INTO orders (total_price, store_id, menu_id, user_id) VALUES (40000, 3, 3, 2);
INSERT INTO orders (total_price, store_id, menu_id, user_id) VALUES (10000, 2, 2, 2);
INSERT INTO orders (total_price, store_id, menu_id, user_id) VALUES (30000, 2, 2, 2);
INSERT INTO orders (total_price, store_id, menu_id, user_id) VALUES (60000, 3, 3, 2);
INSERT INTO orders (total_price, store_id, menu_id, user_id) VALUES (30000, 4, 4, 2);
INSERT INTO orders (total_price, store_id, menu_id, user_id) VALUES (30000, 4, 4, 2);
INSERT INTO orders (total_price, store_id, menu_id, user_id) VALUES (30000, 4, 4, 2);
INSERT INTO orders (total_price, store_id, menu_id, user_id) VALUES (30000, 5, 5, 1);
INSERT INTO orders (total_price, store_id, menu_id, user_id) VALUES (30000, 5, 5, 1);



