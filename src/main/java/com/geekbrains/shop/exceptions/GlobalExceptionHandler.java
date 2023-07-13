package com.geekbrains.shop.exceptions;

import com.geekbrains.shop.exceptions.auth.RegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AppError> registrationException(RegistrationException registrationException){
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), registrationException.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<AppError> tokenException(TokenException tokenException){
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), tokenException.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
