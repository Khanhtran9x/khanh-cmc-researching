CREATE DATABASE `micro_product_service`

create table brand
(
    brand_id     bigint auto_increment
        primary key,
    brand_name   varchar(255) null,
    brand_nation varchar(255) null
);

create table order_product
(
    order_id       bigint auto_increment
        primary key,
    order_numbers  bigint null,
    payment_status bit    null,
    product_id     bigint null
);

create table paid_order
(
    order_id       bigint auto_increment
        primary key,
    order_numbers  bigint null,
    payment_status bit    null,
    product_id     bigint null
);

create table product
(
    product_id   bigint auto_increment
        primary key,
    brand_id     bigint       null,
    product_code varchar(255) null,
    product_name varchar(255) null
);

INSERT INTO micro_product_service.brand (brand_id, brand_name, brand_nation) VALUES (1, 'Tan Hiep Phat', 'Viet Nam');
INSERT INTO micro_product_service.brand (brand_id, brand_name, brand_nation) VALUES (2, 'Cocacola', 'USA');
INSERT INTO micro_product_service.brand (brand_id, brand_name, brand_nation) VALUES (3, 'Pepsi', 'USA');
INSERT INTO micro_product_service.brand (brand_id, brand_name, brand_nation) VALUES (4, 'Adidas', 'Germany');

INSERT INTO micro_product_service.product (product_id, brand_id, product_code, product_name) VALUES (1, 1, 'BR-0001', 'Number 1');
INSERT INTO micro_product_service.product (product_id, brand_id, product_code, product_name) VALUES (2, 3, 'BR-0002', 'Aquafina');

INSERT INTO micro_product_service.order_product (order_id, order_numbers, payment_status, product_id) VALUES (5, 50, false, 1);
INSERT INTO micro_product_service.order_product (order_id, order_numbers, payment_status, product_id) VALUES (6, 100, false, 2);
INSERT INTO micro_product_service.order_product (order_id, order_numbers, payment_status, product_id) VALUES (7, 49, false, 1);

INSERT INTO micro_product_service.paid_order (order_id, order_numbers, payment_status, product_id) VALUES (1, 100, true, 2);
INSERT INTO micro_product_service.paid_order (order_id, order_numbers, payment_status, product_id) VALUES (2, 57, true, 3);