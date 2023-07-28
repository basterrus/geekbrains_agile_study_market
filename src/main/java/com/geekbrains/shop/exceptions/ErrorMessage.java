package com.geekbrains.shop.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class ErrorMessage {
    private final String message;
}
