package com.geekbrains.shop.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Hidden
public class TestController {

    @GetMapping("/api/v1/secured")
    public Principal secured(Principal principal){
        return principal;
    }

    @GetMapping("/api/v1/unsecured")
    public String unsecured(){
        return "unsecured";
    }

    @GetMapping("/api/v1/admin-auth")
    public String adminAuth(){
        return "has role ADMIN";
    }
}
