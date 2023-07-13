package com.geekbrains.shop.controllers;

import com.geekbrains.shop.converters.UserConverter;
import com.geekbrains.shop.models.LoginRequest;
import com.geekbrains.shop.models.RegistrationRequest;
import com.geekbrains.shop.dtos.user.UserDto;
import com.geekbrains.shop.entities.User;
import com.geekbrains.shop.exceptions.auth.RegistrationException;
import com.geekbrains.shop.services.UserService;
import com.geekbrains.shop.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name="Authentication")
public class AuthenticationController {

    private final UserService userService;
    private final UserConverter userConverter;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest) {
        // TODO - добавить валидацию
        String username = registrationRequest.getUsername();
        if (userService.findByUsername(username).isPresent()) {
            throw new RegistrationException(String.format("Username:[%s] exist", username));
        }
        if (userService.findByEmail(registrationRequest.getEmail()).isPresent()) {
            throw new RegistrationException(String.format("User with e-mail:[%s] exist", registrationRequest.getEmail()));
        }

        User user = userService.saveNewUser(registrationRequest);
        String token = jwtTokenUtil.generateToken(user);

        return new ResponseEntity<UserDto>(userConverter.entityToDto(user, token), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequestRequest) {
        //TODO  - сделать логин
        return ResponseEntity.ok("login");
    }


}
