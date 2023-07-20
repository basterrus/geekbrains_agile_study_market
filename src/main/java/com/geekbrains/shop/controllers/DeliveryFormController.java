package com.geekbrains.shop.controllers;

import com.geekbrains.shop.dtos.DeliveryFormDto;
import com.geekbrains.shop.services.DeliveryFormService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/deliveryForms")
@SecurityRequirement(name="Bearer Auth")
@Tag(name="Delivery Forms")
public class DeliveryFormController {

    private final DeliveryFormService deliveryFormService;

    @PostMapping("/deliveryForm")
    public DeliveryFormDto saveDeliveryForm(@RequestBody DeliveryFormDto deliveryFormDto) {
       return deliveryFormService.save(deliveryFormDto);
    }

}
