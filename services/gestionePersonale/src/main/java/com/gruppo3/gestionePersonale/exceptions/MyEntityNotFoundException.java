package com.gruppo3.gestionePersonale.exceptions;

public class MyEntityNotFoundException extends RuntimeException {
    private String message;

    public MyEntityNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
