package com.geekbrains.shop.services;

import com.geekbrains.shop.converters.DeliveryFormConverter;
import com.geekbrains.shop.dtos.DeliveryFormDto;
import com.geekbrains.shop.entities.DeliveryForm;
import com.geekbrains.shop.repositories.DeliveryFormRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliveryFormServiceImpl implements DeliveryFormService {

    private DeliveryFormRepository deliveryFormRepository;
    private DeliveryFormConverter deliveryFormConverter;

    @Override
    public DeliveryFormDto save(DeliveryFormDto deliveryFormDto) {
        DeliveryForm deliveryForm = deliveryFormRepository.save(deliveryFormConverter.toEntity(deliveryFormDto));
        return deliveryFormConverter.toDto(deliveryForm);
    }
}
