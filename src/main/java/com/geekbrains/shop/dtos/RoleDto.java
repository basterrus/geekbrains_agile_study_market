package com.geekbrains.shop.dtos;

import com.geekbrains.shop.entities.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    @PositiveOrZero
    private Long id;

    @NotBlank
    private String name;
}
