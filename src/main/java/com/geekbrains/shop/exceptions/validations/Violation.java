package com.geekbrains.shop.exceptions.validations;

import com.geekbrains.shop.exceptions.ErrorMessage;
import lombok.Getter;


@Getter
public class Violation extends ErrorMessage {
    private final String fieldName;

    public Violation(String message, String fieldName){
        super(message);
        this.fieldName=fieldName;
    }
}
