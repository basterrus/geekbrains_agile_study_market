package com.geekbrains.shop.exceptions.auth;

public class RegistrationException extends RuntimeException{
    public RegistrationException(String message) {
        super(message);
    }
}
