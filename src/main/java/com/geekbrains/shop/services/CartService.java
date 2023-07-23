package com.geekbrains.shop.services;

import com.geekbrains.shop.entities.Product;
import com.geekbrains.shop.exceptions.ResourceNotFoundException;
import com.geekbrains.shop.models.Cart;
import com.geekbrains.shop.models.CartItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;


@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final ProductService productService;
    private final RedisTemplate<String, Object> redisTemplate;
    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;

    public void add(String uuid, Long id) {
        Product product = productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Can't add product to the cart, product id:%s not found", id)));
        log.info("add product method {}", product.getTitle());
        execute(uuid, cart -> cart.add(product));
    }

    public void deleteProductFromCart(String uuid, Long id) {
        execute(uuid, cart -> cart.remove(id));
    }
    public void deleteAllQuantity(String uuid, Long id) {
        execute(uuid, cart -> cart.removeAllQuantity(id));
    }
    private String getTargetUuid(String uuid) {
        return cartPrefix + uuid;
    }
    public Cart getCurrentCart(String uuid) {
        String targetUuid = getTargetUuid(uuid);
        if (Boolean.FALSE.equals(redisTemplate.hasKey(targetUuid))) {
            redisTemplate.opsForValue().set(targetUuid, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(targetUuid);
    }
    public void clear(String uuid) {
        execute(uuid, Cart::clear);
    }

    private void execute(String uuid, Consumer<Cart> operation) {
        Cart cart = getCurrentCart(uuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(getTargetUuid(uuid), cart);
    }

    public void mergeCart(String uuid, String targetUuid) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey(getTargetUuid(uuid)))) {
            Cart guestCart = getCurrentCart(uuid);
            for (CartItem item : guestCart.getItems()) {
                add(targetUuid, item.getProductId());
            }
        }
    }
}