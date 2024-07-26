package com.example.FirstProject.exception.custom;

public class CustomerAlreadyExistsException extends Exception {
    public CustomerAlreadyExistsException(String format) {
        super(format);
    }
}
