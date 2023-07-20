package com.geekbrains.shop.dtos;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryFormDto {

    private Long id;
    private String address;
    private String fullName;
    private String phoneNumber;
    private LocalDate deliveryDate;
}
