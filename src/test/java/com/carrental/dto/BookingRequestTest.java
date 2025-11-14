package com.carrental.dto;

import com.carrental.domain.CarType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookingRequestTest {

    @Test
    void testBookingRequestInitialization() {
        BookingRequest request = new BookingRequest(
                "user@example.com",
                CarType.SEDAN,
                "2025-12-10T10:00",
                3
        );
        assertEquals("user@example.com", request.userEmail());
        assertEquals(CarType.SEDAN, request.carType());
        assertEquals("2025-12-10T10:00", request.startDateTime());
        assertEquals(3, request.numberOfDays());
    }
}
