package com.carrental.exception;

public class CarHasActiveBookingsException extends CarRentalException {
    public CarHasActiveBookingsException(String vin) {
        super("Cannot delete car with VIN '" + vin + "' - it has active bookings");
    }
}
