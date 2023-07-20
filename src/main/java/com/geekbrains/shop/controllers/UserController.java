package com.geekbrains.shop.controllers;

import com.geekbrains.shop.converters.UserConverter;
import com.geekbrains.shop.dtos.RoleDto;
import com.geekbrains.shop.dtos.UserDto;
import com.geekbrains.shop.entities.Role;
import com.geekbrains.shop.entities.User;
import com.geekbrains.shop.exceptions.RoleNotFoundException;
import com.geekbrains.shop.models.RegisterResponse;
import com.geekbrains.shop.models.TokenResponse;
import com.geekbrains.shop.services.RoleService;
import com.geekbrains.shop.services.UserService;
import com.geekbrains.shop.utils.JwtTokenUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Validated
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
    public RegisterResponse setRole(@Valid @RequestBody UserDto userDto) {
        User user = userService.addNewRole(userDto);
        return new RegisterResponse(userConverter.entityToDto(user), new TokenResponse(jwtTokenUtil.generateToken(user)));
    }
}
