package com.geekbrains.shop.exceptions;

public class AppError {
    private int statusCode;
    private Object message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AppError() {
    }

    public AppError(int statusCode, Object message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
