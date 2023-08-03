package com.geekbrains.shop.controllers;

import com.geekbrains.shop.converters.UserConverter;
import com.geekbrains.shop.dtos.token.TokenResponse;
import com.geekbrains.shop.dtos.user.UserDto;
import com.geekbrains.shop.dtos.user.UserRegisterResponse;
import com.geekbrains.shop.entities.User;
import com.geekbrains.shop.services.UserService;
import com.geekbrains.shop.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Validated
@SecurityRequirement(name = "Bearer Auth")
@Tag(name = "Auth")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping()
    public List<UserDto> getAll() {

        List<User> users = userService.findAll();
        return users.stream().map(userConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{userId}")
    @Transactional
    public UserDto delete(@PathVariable @PositiveOrZero Long userId) {
        User user = userService.deleteById(userId);
        return userConverter.entityToDto(user);
    }


    @GetMapping("/{id}")
    public UserDto findById(@PathVariable @PositiveOrZero Long id) {
        User user = userService.findById(id);
        return userConverter.entityToDto(user);
    }

    // присвоение новой роли пользователю
    @PutMapping("/add-role")
    public UserRegisterResponse setRole(@Valid @RequestBody UserDto userDto) {
        User user = userService.addNewRole(userDto);
        return new UserRegisterResponse(userConverter.entityToDto(user), new TokenResponse(jwtTokenUtil.generateToken(user)));
    }
}
