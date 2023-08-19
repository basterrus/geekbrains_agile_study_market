create table orders
(
    id         bigserial
        primary key,
    price      numeric,
    created_at timestamp default CURRENT_TIMESTAMP,
    updated_at timestamp default CURRENT_TIMESTAMP,
    username   varchar
);


create table orders_products
(
    id         bigserial
        primary key,
    order_id   bigint
        references orders,
    product_id bigint
        references products
);


