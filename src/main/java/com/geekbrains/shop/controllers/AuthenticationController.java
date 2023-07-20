package com.geekbrains.shop.controllers;

import com.geekbrains.shop.converters.UserConverter;
import com.geekbrains.shop.models.RegisterResponse;
import com.geekbrains.shop.models.TokenResponse;
import com.geekbrains.shop.entities.User;
import com.geekbrains.shop.models.LoginRequest;
import com.geekbrains.shop.models.RegistrationRequest;
import com.geekbrains.shop.services.UserService;
import com.geekbrains.shop.utils.JwtTokenUtil;
import com.geekbrains.shop.validations.RegistrationRequestValidator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final UserService userService;
    private final UserConverter userConverter;
    private final JwtTokenUtil jwtTokenUtil;
    private final RegistrationRequestValidator registrationRequestValidator;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest registrationRequest) {

        registrationRequestValidator.validate(registrationRequest);

        User user = userService.saveNewUser(registrationRequest);
        String token = jwtTokenUtil.generateToken(user);

        return new ResponseEntity<RegisterResponse>(new RegisterResponse(userConverter.entityToDto(user), new TokenResponse(token)), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return new ResponseEntity<>(new TokenResponse(token), HttpStatus.OK);
    }
}



