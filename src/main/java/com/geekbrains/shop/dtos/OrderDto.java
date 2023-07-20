package com.geekbrains.shop.dtos;


import java.util.List;

import com.geekbrains.shop.models.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private List<CartItem> cartItems;
    private Long deliveryFormId;

}
