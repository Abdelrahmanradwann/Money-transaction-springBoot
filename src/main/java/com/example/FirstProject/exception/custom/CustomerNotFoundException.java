package com.example.FirstProject.exception.custom;

public class CustomerNotFoundException extends Throwable {
    public CustomerNotFoundException(String msg){
        super(msg);
    }
}
