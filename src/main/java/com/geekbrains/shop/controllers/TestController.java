package com.geekbrains.shop.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Hidden
public class TestController {

    @GetMapping("/api/v1/secured")
    public String secured(){
        return "secured";
    }

    @GetMapping("/api/v1/unsecured")
    public String unsecured(){
        return "unsecured";
    }
}
