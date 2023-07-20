package com.geekbrains.shop.converters;

import com.geekbrains.shop.dtos.order.OrderDto;
import com.geekbrains.shop.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {

    private final ProductConverter productConverter;

    public OrderDto entityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setPrice(order.getPrice());
        orderDto.setUuid(order.getUuid());
        orderDto.setProductDtos(order.getProducts().stream().map(productConverter::entityToDto).collect(Collectors.toList()));
        return orderDto;
    }

}
