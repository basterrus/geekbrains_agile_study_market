package com.geekbrains.shop.dtos.user;

import com.geekbrains.shop.dtos.token.TokenResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterResponse {
    private UserDto userDto;
    private TokenResponse tokenResponse;
}
