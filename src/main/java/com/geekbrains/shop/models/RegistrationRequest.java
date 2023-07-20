package com.geekbrains.shop.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationRequest {

    @NotBlank
    private String username;

    @Size(min = 4, max=10)
    @NotBlank
    private String password;

    @Email
    private String email;
}
