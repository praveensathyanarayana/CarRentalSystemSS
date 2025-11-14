package com.carrental.factory;

import com.carrental.domain.Booking;
import com.carrental.domain.Car;
import com.carrental.domain.RentalPeriod;
import com.carrental.domain.vo.Email;
import org.springframework.stereotype.Component;

@Component
public class BookingFactory {
    public Booking createBooking(Car car, Email userEmail, RentalPeriod period) {
        if (car == null) {
            throw new IllegalArgumentException("Car is required");
        }
        if (userEmail == null) {
            throw new IllegalArgumentException("User email is required");
        }
        if (period == null) {
            throw new IllegalArgumentException("Rental period is required");
        }

        period.validate();

        return new Booking(car, userEmail, period);
    }
}
