package com.geekbrains.shop.repositories;

import com.geekbrains.shop.entities.Role;
import com.geekbrains.shop.entities.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleEnum roleName);

}
