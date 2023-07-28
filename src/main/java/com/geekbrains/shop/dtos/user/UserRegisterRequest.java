package com.geekbrains.shop.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterRequest {

    @NotBlank
    private String username;

    @Size(min = 4, max=10)
    @NotBlank
    private String password;

    @Email
    private String email;
}
