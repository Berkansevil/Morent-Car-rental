package com.archisacademy.morent.exceptions;



public class UserAlreadyExistsException  extends RuntimeException{
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
