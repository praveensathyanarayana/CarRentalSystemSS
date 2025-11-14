package com.carrental.factory;

import com.carrental.domain.Booking;
import com.carrental.domain.Car;
import com.carrental.domain.CarType;
import com.carrental.domain.RentalPeriod;
import com.carrental.domain.vo.Email;
import com.carrental.domain.vo.VIN;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingFactoryTest {

    private BookingFactory factory;
    private Car car;
    private Email email;
    private RentalPeriod rentalPeriod;

    @BeforeEach
    void setUp() {
        factory = new BookingFactory();
        car = new Car(new VIN("1HGCM82633A004352"), CarType.SEDAN, "Toyota", "Camry", 70.0);
        email = new Email("user@example.com");
        rentalPeriod = new RentalPeriod(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
    }

    @Test
    void testCreateBookingValid() {
        Booking booking = factory.createBooking(car, email, rentalPeriod);
        assertNotNull(booking);
        assertEquals(car, booking.getCar());
        assertEquals(email, booking.getUserEmail());
        assertEquals(rentalPeriod, booking.getRentalPeriod());
    }

    @Test
    void testCreateBookingNullCar() {
        assertThrows(IllegalArgumentException.class, () ->
                factory.createBooking(null, email, rentalPeriod)
        );
    }

    @Test
    void testCreateBookingNullEmail() {
        assertThrows(IllegalArgumentException.class, () ->
                factory.createBooking(car, null, rentalPeriod)
        );
    }

    @Test
    void testCreateBookingNullPeriod() {
        assertThrows(IllegalArgumentException.class, () ->
                factory.createBooking(car, email, null)
        );
    }

    @Test
    void testCreateBookingInvalidPeriod() {
        RentalPeriod invalidPeriod = new RentalPeriod(LocalDateTime.now().minusDays(1), LocalDateTime.now());
        assertThrows(IllegalArgumentException.class, () ->
                factory.createBooking(car, email, invalidPeriod)
        );
    }
}
