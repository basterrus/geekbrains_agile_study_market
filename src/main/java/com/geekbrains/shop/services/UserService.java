package com.geekbrains.shop.services;


import com.geekbrains.shop.models.RegistrationRequest;
import com.geekbrains.shop.entities.Role;
import com.geekbrains.shop.entities.User;
import com.geekbrains.shop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


    @Transactional
    public User saveNewUser(RegistrationRequest registrationRequest) {
        User user=new User();
        user.setUsername(registrationRequest.getUsername());
        String encodedPassword= bCryptPasswordEncoder.encode(registrationRequest.getPassword());
        user.setPassword(encodedPassword);
        user.setEmail(registrationRequest.getEmail());

        Role role=roleService.findByName("ROLE_USER").orElseThrow(()->new RuntimeException("Role not found"));
        user.setRoles(List.of(role));
        return userRepository.save(user);
    }
}