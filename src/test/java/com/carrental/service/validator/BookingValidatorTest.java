package com.carrental.service.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingValidatorTest {

    private BookingValidator validator;

    @BeforeEach
    void setUp() {
        validator = new BookingValidator();
    }

    @Test
    void testValidBookingRequestDoesNotThrow() {
        assertDoesNotThrow(() -> validator.validateBookingRequest(LocalDateTime.now().plusDays(1), 3));
    }

    @Test
    void testNullStartDateThrows() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                validator.validateBookingRequest(null, 3)
        );
        assertEquals("Start date time is required", ex.getMessage());
    }

    @Test
    void testStartDateInPastThrows() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                validator.validateBookingRequest(LocalDateTime.now().minusDays(1), 3)
        );
        assertEquals("Start date time cannot be in the past", ex.getMessage());
    }

    @Test
    void testZeroOrNegativeDaysThrows() {
        Exception ex1 = assertThrows(IllegalArgumentException.class, () ->
                validator.validateBookingRequest(LocalDateTime.now().plusDays(2), 0)
        );
        Exception ex2 = assertThrows(IllegalArgumentException.class, () ->
                validator.validateBookingRequest(LocalDateTime.now().plusDays(2), -5)
        );
        assertEquals("Number of days must be positive", ex1.getMessage());
        assertEquals("Number of days must be positive", ex2.getMessage());
    }

    @Test
    void testTooManyDaysThrows() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                validator.validateBookingRequest(LocalDateTime.now().plusDays(2), 366)
        );
        assertEquals("Number of days cannot exceed 365", ex.getMessage());
    }
}
