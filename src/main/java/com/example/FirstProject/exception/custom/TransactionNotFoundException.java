package com.example.FirstProject.exception.custom;

public class TransactionNotFoundException extends Exception{
    public TransactionNotFoundException(String msg){
        super(msg);
    }
}
