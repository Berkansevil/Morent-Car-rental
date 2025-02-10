package com.archisacademy.morent.exceptions.book;

public class VehicleAlreadyBookedException extends RuntimeException{
    public VehicleAlreadyBookedException(String message) {
        super(message);
    }
}
