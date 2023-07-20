package com.geekbrains.shop.converters;

import com.geekbrains.shop.dtos.DeliveryFormDto;
import com.geekbrains.shop.entities.DeliveryForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryFormConverter {


    public DeliveryForm toEntity(DeliveryFormDto deliveryFormDto) {
        DeliveryForm deliveryForm = new DeliveryForm();
        deliveryForm.setPhoneNumber(deliveryFormDto.getPhoneNumber());
        deliveryForm.setAddress(deliveryFormDto.getAddress());
        deliveryForm.setFullName(deliveryFormDto.getFullName());
        deliveryForm.setDeliveryDate(deliveryFormDto.getDeliveryDate());
        return deliveryForm;
    }

    public DeliveryFormDto toDto(DeliveryForm deliveryForm) {
        DeliveryFormDto deliveryFormDto = new DeliveryFormDto();
        deliveryFormDto.setPhoneNumber(deliveryForm.getPhoneNumber());
        deliveryFormDto.setAddress(deliveryForm.getAddress());
        deliveryFormDto.setFullName(deliveryForm.getFullName());
        deliveryFormDto.setDeliveryDate(deliveryForm.getDeliveryDate());
        deliveryFormDto.setId(deliveryForm.getId());
        return deliveryFormDto;
    }
}
