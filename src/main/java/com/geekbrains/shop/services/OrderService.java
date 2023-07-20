package com.geekbrains.shop.services;

import com.geekbrains.shop.dtos.OrderDto;
import com.geekbrains.shop.dtos.OrderInfoDto;

public interface OrderService {
    OrderInfoDto createOrder(OrderDto deliveryFormDto);
}
