package com.geekbrains.shop.converters;

import com.geekbrains.shop.dtos.cart.CartItemDto;
import com.geekbrains.shop.models.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemConverter {

    public CartItemDto entityToDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setProductTitle(cartItem.getProductTitle());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());
        cartItemDto.setPrice(cartItem.getPrice());
//        cartItemDto.setImage(cartItem.getImage());
        return cartItemDto;
    }
}
