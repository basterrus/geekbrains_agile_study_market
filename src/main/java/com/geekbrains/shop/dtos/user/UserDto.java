package com.geekbrains.shop.dtos.user;

import com.geekbrains.shop.dtos.role.RoleDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @PositiveOrZero
    private Long id;

    @NotBlank
    private String username;

    @Email
    private String email;

    @Valid
    private List<RoleDto> roles;
}
