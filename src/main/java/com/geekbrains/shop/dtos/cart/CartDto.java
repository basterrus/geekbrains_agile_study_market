package com.geekbrains.shop.dtos.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private List<CartItemDto> items;
    private BigDecimal totalPrice;
}

