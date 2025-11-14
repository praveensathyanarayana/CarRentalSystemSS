package com.carrental.exception;

public class CarNotFoundException extends CarRentalException {
    public CarNotFoundException(String vin) {
        super("Car with VIN '" + vin + "' not found");
    }
}
