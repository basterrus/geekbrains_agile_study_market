package com.geekbrains.shop.exceptions.auth;

import lombok.Data;

import java.util.List;

@Data
public class RegistrationException extends RuntimeException{
    private final List<String> errors;
    public RegistrationException(List<String> errors) {
        super(String.join(", ", errors));
        this.errors=errors;
    }
}
