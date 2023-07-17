package com.geekbrains.shop.controllers;

import com.geekbrains.shop.converters.CartConverter;
import com.geekbrains.shop.dtos.cart.CartDto;

import com.geekbrains.shop.services.CartService;
import com.geekbrains.shop.utils.StringResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;
import java.util.Objects;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
    @RequestMapping("/api/v1/cart")
@Slf4j
public class CartController {
//    private final ProductRepository productRepository;
    private final CartConverter cartConverter;
    private final CartService cartService;

    @GetMapping("/generate_uuid")
    public StringResponse generateUuid() {
        return new StringResponse(UUID.randomUUID().toString());
    }
    @GetMapping("/{uuid}/add/{id}")
    public void addToCart(Principal principal, @PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(principal, uuid);
        cartService.add(targetUuid, id);
    }
    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(Principal principal, @PathVariable String uuid) {
        String targetUuid = getCartUuid(principal, uuid);
        log.info("cart controller uuid: {}", targetUuid);
        return cartConverter.entityToDto(cartService.getCurrentCart(targetUuid));
    }
    @GetMapping("/{uuid}/delete/{id}")
    public void deleteProductFromCart(Principal principal, @PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(principal, uuid);
        cartService.deleteProductFromCart(targetUuid, id);
    }

    @GetMapping("/{uuid}/deleteQuantity/{id}")
    public void deleteAllQuantity(Principal principal, @PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(principal, uuid);
        cartService.deleteAllQuantity(targetUuid, id);
    }

    @GetMapping("/{uuid}/clear")
    public void clearCart(Principal principal, @PathVariable String uuid) {
        String targetUuid = getCartUuid(principal, uuid);
        cartService.clear(targetUuid);
    }
    @GetMapping("/merge/{uuid}")
    public void mergeGuestCart(@PathVariable String uuid, Principal principal){
        String targetUuid = getCartUuid(principal, uuid);
        cartService.mergeCart(uuid, targetUuid);
    }

    private String getCartUuid(Principal principal, String uuid) {
        if (Objects.nonNull(principal)) {
            return principal.getName();
        }
        return uuid;
    }

}
