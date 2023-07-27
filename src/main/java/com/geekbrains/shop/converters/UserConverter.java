package com.geekbrains.shop.converters;

import com.geekbrains.shop.dtos.user.UserDto;
import com.geekbrains.shop.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final RoleConverter roleConverter;

    public UserDto entityToDto(User user) {

        return new UserDto(user.getId(), user.getUsername(), user.getEmail(),
                user.getRoles()
                        .stream()
                        .map(roleConverter::entityToDto)
                        .toList()
                );
    }
}
