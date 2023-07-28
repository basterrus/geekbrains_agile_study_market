package com.geekbrains.shop.exceptions;

import com.geekbrains.shop.exceptions.auth.RegistrationException;
import com.geekbrains.shop.exceptions.validations.ValidationErrorResponse;
import com.geekbrains.shop.exceptions.validations.Violation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {RegistrationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public AppError registrationException(RegistrationException registrationException){
        log.debug("Registration error: {}", registrationException.getErrors());
        return new AppError(HttpStatus.BAD_REQUEST.value(), registrationException.getErrors());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(MethodArgumentNotValidException e) {
        log.debug("Validation error: {}", e.getMessage());
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        log.debug("Validation error: {}", e.getMessage());
        final List<Violation> violations = e.getConstraintViolations().stream()
                .map(
                        violation -> new Violation(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        )
                )
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public AppError badCredentials(BadCredentialsException badCredentialsException){
        log.debug("Authentication error: {}", badCredentialsException.getMessage());
        return new AppError(HttpStatus.BAD_REQUEST.value(), badCredentialsException.getMessage());
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public AppError usernameNotFound(UsernameNotFoundException usernameNotFoundException){
        log.debug("Authentication error: {}", usernameNotFoundException.getMessage());
        return new AppError(HttpStatus.BAD_REQUEST.value(), usernameNotFoundException.getMessage());
    }


    @ExceptionHandler(value = {UserNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public AppError usernameNotFound(UserNotFoundException userNotFoundException){
        log.debug("Error: {}", userNotFoundException.getMessage());
        return new AppError(HttpStatus.BAD_REQUEST.value(), userNotFoundException.getMessage());
    }


    @ExceptionHandler(value = {RoleNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public AppError usernameNotFound(RoleNotFoundException roleNotFoundException){
        log.debug("Role error: {}", roleNotFoundException.getMessage());
        return new AppError(HttpStatus.BAD_REQUEST.value(), roleNotFoundException.getMessage());
    }

}
