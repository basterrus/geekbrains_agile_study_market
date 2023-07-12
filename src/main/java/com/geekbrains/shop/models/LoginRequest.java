package com.geekbrains.shop.models;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
