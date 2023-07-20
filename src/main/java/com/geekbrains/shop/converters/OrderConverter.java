package com.geekbrains.shop.converters;

import java.util.List;
import java.util.stream.Collectors;

import com.geekbrains.shop.dtos.OrderInfoDto;
import com.geekbrains.shop.dtos.ProductDto;
import com.geekbrains.shop.entities.Order;
import com.geekbrains.shop.entities.OrderProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConverter {

    private DeliveryFormConverter deliveryFormConverter;


    public OrderInfoDto toDto(Order order) {
        OrderInfoDto orderInfoDto = new OrderInfoDto();
        orderInfoDto.setProductDtos(getProductFromOrder(order.getOrdersProducts()));
        orderInfoDto.setDeliveryFormDto(deliveryFormConverter.toDto(order.getDeliveryForm()));
        return orderInfoDto;
    }

    private List<ProductDto> getProductFromOrder(List<OrderProduct> ordersProducts) {
        return ordersProducts.stream()
                .map(orderProduct -> {
                    ProductDto productDto = new ProductDto();
                    productDto.setId(orderProduct.getProduct().getId());
                    productDto.setPrice(orderProduct.getProduct().getPrice());
                    productDto.setTitle(orderProduct.getProduct().getTitle());
                    return productDto;
                }).collect(Collectors.toList());
    }
}
