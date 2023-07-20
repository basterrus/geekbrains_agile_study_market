package com.geekbrains.shop.services;

import com.geekbrains.shop.converters.CartConverter;
import com.geekbrains.shop.converters.OrderConverter;
import com.geekbrains.shop.dtos.cart.CartDto;
import com.geekbrains.shop.dtos.cart.CartItemDto;
import com.geekbrains.shop.dtos.product.ProductDto;
import com.geekbrains.shop.entities.Order;
import com.geekbrains.shop.entities.Product;
import com.geekbrains.shop.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final CartService cartService;
    private final ProductService productService;
    private final CartConverter cartConverter;


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public void createOrder(String uuid) {
        Order order = new Order();
        BigDecimal price = new BigDecimal(0);
        List<Product> productsInOrder = new ArrayList<>();
        CartDto cartDto = cartConverter.entityToDto(cartService.getCurrentCart(uuid));
        List<CartItemDto> cartItems = cartDto.getItems();
        for (CartItemDto cartItem : cartItems) {
            price.add(cartItem.getPricePerProduct());
            for (int i = 0; i < cartItem.getQuantity(); i++) {
                productsInOrder.add(productService.findProductById(cartItem.getProductId()).get());
            }
        }
        order.setUuid(uuid);
        order.setPrice(price);
        order.setProducts(productsInOrder);
        cartService.clear(uuid);
        orderRepository.save(order);
    }

    public List<ProductDto> getOrderListById(Long id) {
        List<ProductDto> products = new ArrayList<>();
        Map<ProductDto, Integer> map = new HashMap<>();
        List<ProductDto> productDtos = orderConverter.entityToDto(getOrderById(id).get()).getProductDtos();
        for (ProductDto p : productDtos) {
            if (map.containsKey(p)) {
                map.put(p, map.get(p)+1);
            } else {
                map.put(p, 1);
            }
        }
        for (Map.Entry<ProductDto, Integer> entry : map.entrySet()) {
            ProductDto productDto = entry.getKey();
            productDto.setQuantity(entry.getValue());
            products.add(productDto);
        }
        return products;
    }

    public List<Order> getOrdersByUuid(String uuid){
        return orderRepository.findOrdersByUuid(uuid);
    }

}
