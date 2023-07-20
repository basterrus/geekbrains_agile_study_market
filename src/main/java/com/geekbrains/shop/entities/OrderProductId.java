package com.geekbrains.shop.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class OrderProductId implements Serializable {

    private int order_id;
    private int product_id;
}