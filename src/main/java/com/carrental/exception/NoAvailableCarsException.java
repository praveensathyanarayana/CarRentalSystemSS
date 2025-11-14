package com.carrental.exception;

import com.carrental.domain.CarType;

public class NoAvailableCarsException extends CarRentalException {
    public NoAvailableCarsException(CarType carType) {
        super("No available cars of type " + carType + " for the requested period");
    }
}
