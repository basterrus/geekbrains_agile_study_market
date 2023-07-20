package com.geekbrains.shop.exceptions.validations;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ValidationErrorResponse {
    private final List<Violation> violations;
}
