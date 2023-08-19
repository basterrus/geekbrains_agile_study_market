package com.geekbrains.shop.controllers;

import com.geekbrains.shop.converters.UserConverter;
import com.geekbrains.shop.dtos.token.TokenResponse;
import com.geekbrains.shop.dtos.user.UserLoginRequest;
import com.geekbrains.shop.dtos.user.UserRegisterRequest;
import com.geekbrains.shop.dtos.user.UserRegisterResponse;
import com.geekbrains.shop.entities.User;
import com.geekbrains.shop.services.UserService;
import com.geekbrains.shop.utils.JwtTokenUtil;
import com.geekbrains.shop.validations.UserRegistrationRequestValidator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final UserConverter userConverter;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRegistrationRequestValidator userRegistrationRequestValidator;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegisterResponse register(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {

        userRegistrationRequestValidator.validate(userRegisterRequest);

        User user = userService.saveNew(userRegisterRequest);
        String token = jwtTokenUtil.generateToken(user);

        return new UserRegisterResponse(userConverter.entityToDto(user), new TokenResponse(token));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse login(@Valid @RequestBody UserLoginRequest userLoginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword()));

        String token = jwtTokenUtil.generateToken(authentication);
        return new TokenResponse(token);
    }
}



