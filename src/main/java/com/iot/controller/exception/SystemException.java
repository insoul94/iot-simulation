package com.iot.controller.exception;

public class SystemException extends AppException{

    public SystemException() {
        this("Internal System Error");
    }

    public SystemException(String message) {
        super(message);
    }
}
