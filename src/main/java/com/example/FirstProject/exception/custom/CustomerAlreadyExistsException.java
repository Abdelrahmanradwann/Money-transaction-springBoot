package com.example.FirstProject.exception.custom;

public class CustomerAlreadyExistsException extends Throwable {
    public CustomerAlreadyExistsException(String format) {
        super(format);
    }
}
