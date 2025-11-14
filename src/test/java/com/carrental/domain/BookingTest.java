package com.carrental.domain;

import com.carrental.domain.vo.Email;
import com.carrental.domain.vo.VIN;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {
    private Booking booking;
    private Car car;
    private Email email;
    private RentalPeriod rentalPeriod;

    @BeforeEach
    void setUp() {
        VIN vin = new VIN("1HGCM82633A004352"); // 17-char valid VIN
        car = new Car(vin, CarType.SEDAN, "Toyota", "Camry", 79.99);
        email = new Email("test@example.com");
        rentalPeriod = new RentalPeriod(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(5));
        booking = new Booking(car, email, rentalPeriod);
    }

    @Test
    void testBookingInitialization() {
        assertNotNull(booking.getBookingId());
        assertEquals(car, booking.getCar());
        assertEquals(email, booking.getUserEmail());
        assertEquals(rentalPeriod, booking.getRentalPeriod());
        assertFalse(booking.isReturned());
        assertEquals(0.0, booking.getAmountCharged());
    }

    @Test
    void testMarkAsReturnedValidCharge() {
        double charge = 100.0;
        booking.markAsReturned(charge);
        assertTrue(booking.isReturned());
        assertEquals(charge, booking.getAmountCharged());
    }

    @Test
    void testMarkAsReturnedThrowsIfAlreadyReturned() {
        booking.markAsReturned(50.0);
        assertThrows(IllegalStateException.class, () -> booking.markAsReturned(80.0));
    }

    @Test
    void testMarkAsReturnedThrowsIfNegativeCharge() {
        assertThrows(IllegalArgumentException.class, () -> booking.markAsReturned(-10.0));
    }

    @Test
    void testIsActive() {
        assertTrue(booking.isActive());
        booking.markAsReturned(55.0);
        assertFalse(booking.isActive());
    }
}
