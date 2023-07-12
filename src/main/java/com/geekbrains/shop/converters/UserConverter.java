package com.geekbrains.shop.converters;

import com.geekbrains.shop.dtos.user.UserDto;
import com.geekbrains.shop.entities.Role;
import com.geekbrains.shop.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserDto entityToDto(User user, String token) {

        return new UserDto(user.getUsername(), user.getEmail(), user.getRoles().stream()
                .map(Role::getName).toList(), token);
    }
}
