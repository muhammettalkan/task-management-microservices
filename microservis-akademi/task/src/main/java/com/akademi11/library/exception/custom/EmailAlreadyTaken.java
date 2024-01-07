package com.akademi11.library.exception.custom;

public class EmailAlreadyTaken extends RuntimeException{
    public EmailAlreadyTaken(String message){
        super(message);
    }

}
