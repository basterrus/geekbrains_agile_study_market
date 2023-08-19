package com.geekbrains.shop.converters;

import com.geekbrains.shop.dtos.role.RoleDto;
import com.geekbrains.shop.entities.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {
    public RoleDto entityToDto(Role role){
        return new RoleDto(role.getId(), role.getName().getName());
    }
}
