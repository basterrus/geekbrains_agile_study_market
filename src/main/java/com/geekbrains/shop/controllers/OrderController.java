package com.geekbrains.shop.controllers;

import com.geekbrains.shop.dtos.OrderDto;
import com.geekbrains.shop.dtos.OrderInfoDto;
import com.geekbrains.shop.services.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@SecurityRequirement(name="Bearer Auth")
@Tag(name="Orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderInfoDto createOrder(@RequestBody OrderDto orderDto) {
       return orderService.createOrder(orderDto);
    }

}
