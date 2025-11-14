package com.carrental.exception;

public class BookingNotFoundException extends CarRentalException {
    public BookingNotFoundException(String bookingId) {
        super("Booking with ID '" + bookingId + "' not found");
    }
}
