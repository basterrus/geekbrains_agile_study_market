package com.geekbrains.shop.validations;

import com.geekbrains.shop.dtos.user.UserRegisterRequest;
import com.geekbrains.shop.exceptions.auth.RegistrationException;
import com.geekbrains.shop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserRegistrationRequestValidator {

    private final UserService userService;

    List<String> errors=new ArrayList<>();

    public void validate(UserRegisterRequest userRegisterRequest) {

        if (userService.findByUsername(userRegisterRequest.getUsername()).isPresent()) {
            errors.add(String.format("Username:[%s] exist", userRegisterRequest.getUsername()));
        }

        if (userService.findByEmail(userRegisterRequest.getEmail()).isPresent()) {
            errors.add(String.format("User with e-mail:[%s] exist", userRegisterRequest.getEmail()));
        }

        if(!errors.isEmpty()){
            throw new RegistrationException(errors);
        }
    }
}
