package com.iot.controller.exception;

public class UserException extends AppException{

    public UserException() {
        this("Invalid Input Data");
    }

    public UserException(String message) {
        super(message);
    }
}
