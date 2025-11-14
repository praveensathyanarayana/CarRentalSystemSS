package com.carrental.dto;

import com.carrental.domain.Booking;
import com.carrental.domain.Car;
import com.carrental.domain.CarType;
import com.carrental.domain.vo.Email;
import com.carrental.domain.vo.VIN;
import com.carrental.domain.RentalPeriod;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingResponseTest {

    @Test
    void testBookingResponseInitialization() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusDays(2);
        Booking booking = new Booking(
                new Car(new VIN("1HGCM82633A004352"), CarType.SEDAN, "Toyota", "Camry", 70.0),
                new Email("user@example.com"),
                new RentalPeriod(start, end)
        );

        BookingResponse response = BookingResponse.from(booking);
        assertEquals(booking.getBookingId(), response.bookingId());
        assertEquals("1HGCM82633A004352", response.carVin());
        assertEquals("user@example.com", response.userEmail());
        assertEquals(start, response.startDate());
        assertEquals(end, response.endDate());
        assertEquals(booking.getAmountCharged(), response.amountCharged());
        assertEquals(booking.isReturned(), response.returned());
    }
}
