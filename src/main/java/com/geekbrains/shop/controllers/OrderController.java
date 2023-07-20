package com.geekbrains.shop.controllers;

import com.geekbrains.shop.converters.OrderConverter;
import com.geekbrains.shop.dtos.order.OrderDto;
import com.geekbrains.shop.dtos.product.ProductDto;
import com.geekbrains.shop.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @GetMapping("/{uuid}")
    public List<OrderDto> getAllOrders(@PathVariable String uuid) {
        return orderService.getOrdersByUuid(uuid).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }

    @PostMapping("/{uuid}/createOrder")
    public ResponseEntity<?> createOrder (@PathVariable String uuid) {
        orderService.createOrder(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
