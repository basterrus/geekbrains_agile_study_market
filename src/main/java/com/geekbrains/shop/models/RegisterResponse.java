package com.geekbrains.shop.models;

import com.geekbrains.shop.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResponse {
    private UserDto userDto;
    private TokenResponse tokenResponse;
}
