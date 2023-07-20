package com.geekbrains.shop.models;


import com.geekbrains.shop.entities.Product;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.geekbrains.shop.entities.Product;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;
    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(Product product) {
        for (CartItem item : items) {
            if (product.getId().equals(item.getProductId())) {
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(mapToCartItem(product));
        recalculate();
    }
    public void remove(Long id) {
        for (CartItem item : items) {
            if (item.getProductId().equals(id)) {
                if (item.getQuantity() >= 2) {
                    item.changeQuantity(-1);
                    recalculate();
                    return;
                }
                return;
            }
        }
    }
    public void clear() {
        items.clear();
        totalPrice = BigDecimal.valueOf(0);
    }
    public void removeAllQuantity(Long id) {
        if (items.removeIf(p -> p.getProductId().equals(id))) {
            recalculate();
        }
    }

    private void recalculate() {
        totalPrice = BigDecimal.ZERO;
        totalPrice = items.stream().map(CartItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
    }
    private CartItem mapToCartItem(Product product) {
        return new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice());
    }
}
