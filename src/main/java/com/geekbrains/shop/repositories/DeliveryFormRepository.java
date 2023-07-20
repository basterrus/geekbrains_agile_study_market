package com.geekbrains.shop.repositories;

import com.geekbrains.shop.entities.DeliveryForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryFormRepository extends JpaRepository<DeliveryForm, Long> {


}
