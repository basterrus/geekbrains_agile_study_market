package com.geekbrains.shop.services;


import com.geekbrains.shop.dtos.user.UserDto;
import com.geekbrains.shop.dtos.user.UserRegisterRequest;
import com.geekbrains.shop.entities.Role;
import com.geekbrains.shop.entities.RoleEnum;
import com.geekbrains.shop.entities.User;
import com.geekbrains.shop.exceptions.RoleNotFoundException;
import com.geekbrains.shop.exceptions.UserNotFoundException;
import com.geekbrains.shop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Transactional
    public User saveNew(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setUsername(userRegisterRequest.getUsername());
        String encodedPassword = bCryptPasswordEncoder.encode(userRegisterRequest.getPassword());
        user.setPassword(encodedPassword);
        user.setEmail(userRegisterRequest.getEmail());

        Role role = roleService.findByName(RoleEnum.ROLE_USER)
                .orElseThrow(() -> new RoleNotFoundException(String.format("Role {%s} not found", RoleEnum.ROLE_USER.getName())));
        user.setRoles(List.of(role));
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User [%s] not found", id)));
        userRepository.deleteById(id);
        return user;
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found: id=[%s]", id)));
    }

    @Transactional
    public User addNewRole(UserDto userDto) {
        User user = this.findById(userDto.getId());
        List<Role> roles = userDto.getRoles()
                .stream()
                .map(role -> roleService.findById(role.getId())
                        .orElseThrow(() -> new RoleNotFoundException(String.format("Role not found: {%s}", role.getName()))))
                .toList();
        user.getRoles().clear();
        user.getRoles().addAll(roles);
        return user;
    }
}