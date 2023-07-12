package com.geekbrains.shop.services;

import com.geekbrains.shop.entities.Role;
import com.geekbrains.shop.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Optional<Role> findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
