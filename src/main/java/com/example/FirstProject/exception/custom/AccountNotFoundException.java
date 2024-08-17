package com.example.FirstProject.exception.custom;

public class AccountNotFoundException extends Exception{
    public AccountNotFoundException(String msg){
        super(msg);
    }
}
