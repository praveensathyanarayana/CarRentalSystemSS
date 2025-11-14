package com.carrental.exception;

public class DuplicateCarException extends CarRentalException {
    public DuplicateCarException(String vin) {
        super("Car with VIN '" + vin + "' already exists");
    }
}
