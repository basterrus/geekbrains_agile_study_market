package com.geekbrains.shop.services;

import java.util.List;

import com.geekbrains.shop.converters.OrderConverter;
import com.geekbrains.shop.dtos.OrderDto;
import com.geekbrains.shop.dtos.OrderInfoDto;
import com.geekbrains.shop.entities.Order;
import com.geekbrains.shop.entities.OrderProduct;
import com.geekbrains.shop.entities.Product;
import com.geekbrains.shop.models.CartItem;
import com.geekbrains.shop.repositories.OrderRepository;
import com.geekbrains.shop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderConverter orderConverter;

    @Override
    public OrderInfoDto createOrder(OrderDto orderDto) {
        List<CartItem> cartItems = orderDto.getCartItems();
        Order order = new Order();
        // set other order properties
        for (CartItem cartItem : cartItems) {
            Long productId = cartItem.getProductId();
            int quantity = cartItem.getQuantity();
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product id: " + productId));

            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(product);
            orderProduct.setQuantity(quantity);
            order.getOrdersProducts().add(orderProduct);
        }
        Order save = orderRepository.save(order);
        return orderConverter.toDto(save);
    }
}
