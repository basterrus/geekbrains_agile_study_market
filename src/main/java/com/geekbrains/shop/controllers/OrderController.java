package com.geekbrains.shop.controllers;

import com.geekbrains.shop.converters.OrderConverter;
import com.geekbrains.shop.dtos.order.OrderDto;
import com.geekbrains.shop.dtos.product.ProductDto;
import com.geekbrains.shop.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @GetMapping
    public List<OrderDto> getAllOrders(Principal principal) {
        return orderService.getOrdersByUsername(principal.getName()).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }

    @PostMapping("/{uuid}/createOrder")
    public ResponseEntity<?> createOrder (Principal principal, @PathVariable String uuid) {
        String targetUuid = getCartUuid(principal, uuid);
        orderService.createOrder(principal.getName(), targetUuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String getCartUuid(Principal principal, String uuid) {
        if (Objects.nonNull(principal)) {
            return principal.getName();
        }
        return uuid;
    }

}
