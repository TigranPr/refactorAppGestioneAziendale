package com.gruppo3.gestioneCurriculum.exceptions;

public class MyIllegalException extends RuntimeException {
    private String message;

    public MyIllegalException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
