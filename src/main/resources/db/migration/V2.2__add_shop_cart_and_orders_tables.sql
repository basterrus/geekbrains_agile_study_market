CREATE TABLE orders
(
    id               INT PRIMARY KEY,
    user_id          INT,
    order_date       DATE,
    delivery_form_id INT,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (user_id) REFERENCES delivery_form (delivery_id)
);

CREATE TABLE order_products
(
    order_id   INT,
    product_id INT,
    quantity   INT,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE delivery_forms
(
    delivery_id      INT PRIMARY KEY,
    order_id         INT,
    delivery_date    DATE,
    delivery_address VARCHAR(100)
);