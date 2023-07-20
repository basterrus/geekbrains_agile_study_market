package com.geekbrains.shop.dtos;


import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDto {

    private Long id;
    private LocalDate creatingDate;
    private List<ProductDto> productDtos;
    private DeliveryFormDto deliveryFormDto;

}
