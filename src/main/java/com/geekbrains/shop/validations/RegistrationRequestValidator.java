package com.geekbrains.shop.validations;

import com.geekbrains.shop.exceptions.auth.RegistrationException;
import com.geekbrains.shop.models.RegistrationRequest;
import com.geekbrains.shop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationRequestValidator {

    private final UserService userService;

    public void validate(RegistrationRequest registrationRequest) {

        if (userService.findByUsername(registrationRequest.getUsername()).isPresent()) {
            throw new RegistrationException(String.format("Username:[%s] exist", registrationRequest.getUsername()));
        }
        if (userService.findByEmail(registrationRequest.getEmail()).isPresent()) {
            throw new RegistrationException(String.format("User with e-mail:[%s] exist", registrationRequest.getEmail()));
        }
    }
}
