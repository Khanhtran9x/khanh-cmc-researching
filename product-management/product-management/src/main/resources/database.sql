CREATE DATABASE `product_management`;

create table if not exists product_management.product_order
(
    id                 int auto_increment
    primary key,
    numbers_of_product int          null,
    product_name       varchar(255) null
    );

INSERT INTO product_management.product_order (id, numbers_of_product, product_name) VALUES (72, 10, 'Coca');
INSERT INTO product_management.product_order (id, numbers_of_product, product_name) VALUES (73, 10, 'Pepsi');
INSERT INTO product_management.product_order (id, numbers_of_product, product_name) VALUES (74, 10, 'Pepsi');
INSERT INTO product_management.product_order (id, numbers_of_product, product_name) VALUES (75, 10, 'Aquafina');
INSERT INTO product_management.product_order (id, numbers_of_product, product_name) VALUES (76, 10, 'Sting');
INSERT INTO product_management.product_order (id, numbers_of_product, product_name) VALUES (77, 10, 'C2');
INSERT INTO product_management.product_order (id, numbers_of_product, product_name) VALUES (78, 10, 'Coca');
INSERT INTO product_management.product_order (id, numbers_of_product, product_name) VALUES (79, 10, 'Pepsi');
INSERT INTO product_management.product_order (id, numbers_of_product, product_name) VALUES (80, 10, 'Pepsi');
INSERT INTO product_management.product_order (id, numbers_of_product, product_name) VALUES (81, 10, 'Aquafina');