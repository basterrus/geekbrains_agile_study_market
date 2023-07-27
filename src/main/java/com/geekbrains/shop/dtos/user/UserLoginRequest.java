package com.geekbrains.shop.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 4, max=10)
    private String password;
}
